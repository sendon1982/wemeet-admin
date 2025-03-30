package org.wemeet.portal.service.gstone;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.wemeet.portal.domain.BoardGameV2;
import org.wemeet.portal.domain.RelationGameIndex;
import org.wemeet.portal.model.gstone.*;
import org.wemeet.portal.repository.BoardGameV2Repository;
import org.wemeet.portal.util.JsonUtil;

@Service
@Slf4j
@RequiredArgsConstructor
public class GstoneServiceImpl implements GstoneService {

    private final BoardGameV2Repository boardGameV2Repository;

    @Override
    public BoardGameV2 findGameById(int gameId, Boolean includeExpansion) {
        BoardGameV2 boardGame = boardGameV2Repository.findGameById(gameId);

        if (boardGame.isExpansion()) {
            // Expansion
            log.info("GameId {} and name {} is an expansion game", gameId, boardGame.getChineseName());
        } else {
            if (includeExpansion != null && includeExpansion) {
                log.info("GameId {} and name {} is an standard game and need to include expansion", gameId, boardGame.getChineseName());

                Set<RelationGameIndex> gameIndices = boardGame
                    .getRelationGameIndices()
                    .stream()
                    .filter(p -> p.expansion_type == 751 && p.is_expansion == 1)
                    .collect(Collectors.toSet());

                boardGame.setRelationGameIndices(new ArrayList<>(gameIndices));
            }
        }

        return boardGame;
    }

    @Override
    public List<BoardGameV2> getGameListByIds(List<Integer> gameIds) {
        return boardGameV2Repository.getGameListByIds(gameIds);
    }

    @Override
    public List<BoardGameV2> searchGames(String name, Integer minPlayers, Integer maxPlayers, String category, String theme) {
        if (StringUtils.isNotBlank(name)) {
            return boardGameV2Repository.findByName(name);
        }

        if (minPlayers != null && maxPlayers != null) {
            return boardGameV2Repository.findByMinPlayersLessThanEqualAndMaxPlayersGreaterThanEqual(minPlayers, maxPlayers);
        }

        if (category != null) {
            return boardGameV2Repository.findByCategoriesContaining(category);
        }

        if (theme != null) {
            return boardGameV2Repository.findByThemesContaining(theme);
        }

        return Collections.emptyList();
    }

    @SneakyThrows
    @Override
    public GstoneResponse getRankList() {
        String url = "https://www.gstonegames.com/app/v2/now_pop_list_get/";

        for (int k = 11; k <= 20; k++) {
            GstoneNowPopListRequest request = new GstoneNowPopListRequest();
            request.setZoneId(1);
            request.setPage(k);

            String json = JsonUtil.convertToString(request);

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true); // Important for POST

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            String inputLine = null;
            StringBuilder response = new StringBuilder();

            try (java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(con.getInputStream()))) {
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }

            GstoneResponse gstoneResponse = JsonUtil.convertToObject(response.toString(), GstoneResponse.class);
            List<Game> gameList = gstoneResponse.getData().getGameList();
            int count = 0;

            for (Game game : gameList) {
                int gameId = game.getId();

                BoardGameV2 existingGame = boardGameV2Repository.findGameById(gameId);

                if (existingGame != null) {
                    log.info("skipping existing game {} - {}", gameId, game.getName());
                    continue;
                }

                BoardGameV2 boardGameV2 = new BoardGameV2();

                boardGameV2.setGameId(gameId);
                boardGameV2.setEnglishName(game.getName());
                boardGameV2.setChineseName(game.getName());
                boardGameV2.setTotalTime(game.getTotalTime());
                boardGameV2.setAverageTimePerPlayer(game.getAverageTimePerPlayer());
                boardGameV2.setPrimaryLanguage(game.getPrimaryLanguage());

                List<Category> categoryList = game.getCategory();
                Set<String> categories = categoryList.stream().map(Category::getValue).collect(Collectors.toSet());
                boardGameV2.setCategories(categories);

                List<Theme> themeList = game.getTheme();
                Set<String> themes = themeList.stream().map(Theme::getValue).collect(Collectors.toSet());
                boardGameV2.setThemes(themes);

                boardGameV2.setMode(game.getMode().getValue());

                boardGameV2.setBoxUrl(game.getBoxUrl());
                boardGameV2.setCoverUrl(game.getCoverUrl());
                boardGameV2.setStatus(game.getStatus().getValue());
                boardGameV2.setDifficulty(game.getDifficulty());
                boardGameV2.setPublishYear(game.getPublishYear());

                List<Integer> playerNums = game.getPlayerNum();
                boardGameV2.setPlayerNums(playerNums);

                int minPlayers = 0;
                int maxPlayers = 0;

                for (int i = 0; i < playerNums.size(); i++) {
                    if (playerNums.get(i) >= 1) {
                        minPlayers = i + 1;
                        break;
                    }
                }

                for (int i = playerNums.size() - 1; i >= 0; i--) {
                    if (playerNums.get(i) >= 1) {
                        maxPlayers = i + 1;
                        break;
                    }
                }

                boardGameV2.setMinPlayers(minPlayers);
                boardGameV2.setMaxPlayers(maxPlayers);

                boardGameV2.setGameHotnessValue(game.getGameHotnessValue());
                boardGameV2.setWemeetRating(0);
                boardGameV2.setGstoneRating(game.getGstoneRating());

                boardGameV2.setCreatedAt(LocalDate.now());
                boardGameV2.setUpdatedAt(LocalDate.now());
                boardGameV2Repository.save(boardGameV2);

                count++;
            }

            log.info("================================================================");
            log.info("Games saved count is {}", count);

            Thread.sleep(3000);
        }

        return null;
    }

    @SneakyThrows
    public void getGameInfo() {
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        // Get all gameIds
        List<BoardGameV2> boardGameV2List = boardGameV2Repository.findAll();

        /**
         *         List<BoardGameV2> boardGameV2List = new ArrayList<>();
         *         BoardGameV2 gameV2 = new BoardGameV2();
         *         gameV2.setGameId(29568);
         *         boardGameV2List.add(gameV2);
         */

        String url = "https://www.gstonegames.com/app/v2/game_info_get/";

        for (BoardGameV2 boardGameV2 : boardGameV2List) {
            executorService.execute(() -> {
                final int gameId = boardGameV2.getGameId();

                GameInfoRequest request = new GameInfoRequest();
                request.setGameId(gameId);

                String json = JsonUtil.convertToString(request);

                URL obj = null;
                try {
                    obj = new URL(url);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                HttpURLConnection con = null;
                try {
                    con = (HttpURLConnection) obj.openConnection();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                try {
                    con.setRequestMethod("POST");
                } catch (ProtocolException e) {
                    throw new RuntimeException(e);
                }
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true); // Important for POST

                try (OutputStream os = con.getOutputStream()) {
                    byte[] input = json.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                } catch (Exception e) {}

                String inputLine = null;
                StringBuilder response = new StringBuilder();

                try (java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(con.getInputStream()))) {
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                } catch (Exception e) {}

                GameInfoResponse gameInfoResponse = JsonUtil.convertToObject(response.toString(), GameInfoResponse.class);
                BoardGameV2 fetchedBoardGameV2 = boardGameV2Repository.findGameById(gameId);

                if (gameInfoResponse.getData() != null && gameInfoResponse.getData().getGame_info() != null) {
                    GameInfo gameInfo = gameInfoResponse.getData().getGame_info();
                    if (gameInfo.id == gameId) {
                        // same game
                        fetchedBoardGameV2.setEnglishName(gameInfo.p_name);
                        fetchedBoardGameV2.setChineseName(gameInfo.name);
                        fetchedBoardGameV2.setEnglishDescription(gameInfo.p_description);
                        fetchedBoardGameV2.setChineseDescription(gameInfo.description);
                        fetchedBoardGameV2.setBggId(gameInfo.bgg_id);

                        fetchedBoardGameV2.setExpansion(gameInfo.isExpansion());
                        fetchedBoardGameV2.setExpansionType(gameInfo.getExpansionType());

                        if (gameInfo.getRelationInfo() != null) {
                            RelationInfo relationInfo = gameInfo.getRelationInfo();
                            fetchedBoardGameV2.setRelationGameIds(relationInfo.getRelationGameIds());
                            fetchedBoardGameV2.setRelationGameIndices(relationInfo.getRelationIndices());
                        }

                        boardGameV2Repository.save(fetchedBoardGameV2);

                        System.out.println("Updating boardgame info for gameId = " + gameId);
                    }
                }
            });
        }
    }
}

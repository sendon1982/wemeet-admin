package org.wemeet.portal.service.gstone;

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
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
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

    public static final String GAME_INFO_GET_URL = "https://www.gstonegames.com/app/v2/game_info_get/";

    private final BoardGameV2Repository boardGameV2Repository;

    @Override
    public BoardGameV2 findGameById(int gameId, Boolean includeExpansion) {
        BoardGameV2 boardGame = boardGameV2Repository.findGameById(gameId);

        if (BooleanUtils.isTrue(boardGame.getIsExpansion())) {
            // Expansion
            log.info("GameId {} and name {} is an expansion game", gameId, boardGame.getChineseName());
        } else {
            if (includeExpansion != null && includeExpansion) {
                log.info("GameId {} and name {} is an standard game and need to include expansion", gameId, boardGame.getChineseName());

                if (boardGame.getRelationGameIndices() == null) {
                    return boardGame;
                }

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

    @Override
    public List<BoardGameV2> findAllGames() {
        return boardGameV2Repository.findAll();
    }

    @SneakyThrows
    public GstoneResponse getRankList() {
        String url = "https://www.gstonegames.com/app/v2/rank_list_get/";

        for (int k = 1; k <= 13; k++) {
            GstoneRankListRequest request = new GstoneRankListRequest();
            request.setCategory(2);
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

                BoardGameV2 boardGameV2 = new BoardGameV2();
                BoardGameV2 existingGame = boardGameV2Repository.findGameById(gameId);

                if (existingGame != null) {
                    log.info("Updating existing game {} - {}", gameId, game.getChineseName());
                    boardGameV2 = existingGame;
                } else {
                    log.info("Saving new game {} - {}", gameId, game.getChineseName());
                }

                // GameInfoResponse gameInfo = getGameInfo(gameId);
                GameInfoResponse gameInfo = null;
                game = gameInfo.getData().getGame_info();

                boardGameV2.setEnglishName(game.getEnglishName());
                boardGameV2.setChineseName(game.getChineseName());

                if (StringUtils.isBlank(game.getChineseDescription())) {
                    boardGameV2.setChineseDescription(game.getEnglishDescription());
                } else {
                    boardGameV2.setChineseDescription(game.getChineseDescription());
                }

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

                MinMaxPlayer minMaxPlayer = calcMinMaxPlayerCount(playerNums);
                int minPlayers = minMaxPlayer.minPlayers();
                int maxPlayers = minMaxPlayer.maxPlayers();

                boardGameV2.setMinPlayers(minPlayers);
                boardGameV2.setMaxPlayers(maxPlayers);

                boardGameV2.setBggId(game.getBggId());

                boardGameV2.setIsExpansion(game.getIsExpansion() == 1);
                boardGameV2.setExpansionType(game.getExpansionType());

                if (game.getRelationInfo() != null) {
                    RelationInfo relationInfo = game.getRelationInfo();
                    boardGameV2.setRelationGameIds(relationInfo.getRelationGameIds());
                    boardGameV2.setRelationGameIndices(relationInfo.getRelationIndices());
                }

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
    public GstoneResponse getPopularListGames() {
        String url = "https://www.gstonegames.com/app/v2/now_pop_list_get/";

        for (int k = 1; k <= 1181; k++) {
            GstoneNowPopListRequest request = new GstoneNowPopListRequest();
            request.setZoneId(0);
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

                BoardGameV2 boardGameV2 = new BoardGameV2();
                BoardGameV2 existingGame = boardGameV2Repository.findGameById(gameId);

                if (existingGame != null) {
                    log.info("Updating existing game {} - {}", gameId, game.getChineseName());
                    boardGameV2 = existingGame;
                } else {
                    log.info("Saving new game {} - {}", gameId, game.getChineseName());
                }

                boardGameV2.setGameId(gameId);

                boardGameV2.setEnglishName(game.getEnglishName());
                boardGameV2.setChineseName(game.getChineseName());

                if (StringUtils.isBlank(game.getChineseDescription())) {
                    boardGameV2.setChineseDescription(game.getEnglishDescription());
                } else {
                    boardGameV2.setChineseDescription(game.getChineseDescription());
                }

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

                MinMaxPlayer minMaxPlayer = calcMinMaxPlayerCount(playerNums);
                int minPlayers = minMaxPlayer.minPlayers();
                int maxPlayers = minMaxPlayer.maxPlayers();

                boardGameV2.setMinPlayers(minPlayers);
                boardGameV2.setMaxPlayers(maxPlayers);

                boardGameV2.setBggId(game.getBggId());

                boardGameV2.setIsExpansion(game.getIsExpansion() == 1);
                boardGameV2.setExpansionType(game.getExpansionType());

                if (game.getRelationInfo() != null) {
                    RelationInfo relationInfo = game.getRelationInfo();
                    boardGameV2.setRelationGameIds(relationInfo.getRelationGameIds());
                    boardGameV2.setRelationGameIndices(relationInfo.getRelationIndices());
                }

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

    public GameInfoResponse getGameInfo(int gameId) {
        GameInfoRequest request = new GameInfoRequest();
        request.setGameId(gameId);

        String json = JsonUtil.convertToString(request);

        URL obj = null;
        try {
            obj = new URL(GAME_INFO_GET_URL);
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

        return JsonUtil.convertToObject(response.toString(), GameInfoResponse.class);
    }

    @SneakyThrows
    public void populateGameInfo() {
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        // Get all gameIds
        //        List<BoardGameV2> boardGameV2List = boardGameV2Repository.findAll();

        List<BoardGameV2> boardGameV2List = new ArrayList<>();
        BoardGameV2 gameV2 = new BoardGameV2();
        gameV2.setGameId(45141);
        boardGameV2List.add(gameV2);

        for (BoardGameV2 boardGameV2 : boardGameV2List) {
            executorService.execute(() -> {
                final int gameId = boardGameV2.getGameId();

                GameInfoRequest request = new GameInfoRequest();
                request.setGameId(gameId);

                String json = JsonUtil.convertToString(request);

                URL obj = null;
                try {
                    obj = new URL(GAME_INFO_GET_URL);
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

                if (fetchedBoardGameV2 == null) {
                    fetchedBoardGameV2 = new BoardGameV2();
                }

                if (gameInfoResponse.getData() != null && gameInfoResponse.getData().getGame_info() != null) {
                    Game game = gameInfoResponse.getData().getGame_info();

                    fetchedBoardGameV2.setGameId(gameId);
                    fetchedBoardGameV2.setTotalTime(game.getTotalTime());
                    fetchedBoardGameV2.setAverageTimePerPlayer(game.getAverageTimePerPlayer());
                    fetchedBoardGameV2.setPrimaryLanguage(game.getPrimaryLanguage());

                    fetchedBoardGameV2.setExpansionType(game.getExpansionType());
                    fetchedBoardGameV2.setIsExpansion(game.getIsExpansion() == 1);

                    // same game
                    fetchedBoardGameV2.setEnglishName(game.getEnglishName());

                    if (StringUtils.isBlank(game.getChineseName())) {
                        fetchedBoardGameV2.setChineseName(game.getEnglishName());
                    } else {
                        fetchedBoardGameV2.setChineseName(game.getChineseName());
                    }

                    fetchedBoardGameV2.setEnglishDescription(game.getEnglishDescription());

                    if (StringUtils.isBlank(game.getChineseDescription())) {
                        fetchedBoardGameV2.setChineseDescription(game.getEnglishDescription());
                    } else {
                        fetchedBoardGameV2.setChineseDescription(game.getChineseDescription());
                    }

                    List<Category> categoryList = game.getCategory();
                    Set<String> categories = categoryList.stream().map(Category::getValue).collect(Collectors.toSet());
                    fetchedBoardGameV2.setCategories(categories);

                    List<Theme> themeList = game.getTheme();
                    Set<String> themes = themeList.stream().map(Theme::getValue).collect(Collectors.toSet());
                    fetchedBoardGameV2.setThemes(themes);

                    fetchedBoardGameV2.setMode(game.getMode().getValue());

                    fetchedBoardGameV2.setBoxUrl(game.getBoxUrl());
                    fetchedBoardGameV2.setCoverUrl(game.getCoverUrl());
                    fetchedBoardGameV2.setStatus(game.getStatus().getValue());
                    fetchedBoardGameV2.setDifficulty(game.getDifficulty());
                    fetchedBoardGameV2.setPublishYear(game.getPublishYear());

                    fetchedBoardGameV2.setBggId(game.getBggId());

                    if (game.getRelationInfo() != null) {
                        RelationInfo relationInfo = game.getRelationInfo();
                        fetchedBoardGameV2.setRelationGameIds(relationInfo.getRelationGameIds());
                        fetchedBoardGameV2.setRelationGameIndices(relationInfo.getRelationIndices());
                    }

                    List<Integer> playerNums = game.getPlayerNum();
                    fetchedBoardGameV2.setPlayerNums(playerNums);

                    MinMaxPlayer minMaxPlayer = calcMinMaxPlayerCount(playerNums);

                    fetchedBoardGameV2.setMinPlayers(minMaxPlayer.minPlayers());
                    fetchedBoardGameV2.setMaxPlayers(minMaxPlayer.maxPlayers());

                    fetchedBoardGameV2.setGameHotnessValue(game.getGameHotnessValue());
                    fetchedBoardGameV2.setWemeetRating(0);
                    fetchedBoardGameV2.setGstoneRating(game.getGstoneRating());

                    fetchedBoardGameV2.setCreatedAt(LocalDate.now());
                    fetchedBoardGameV2.setUpdatedAt(LocalDate.now());

                    boardGameV2Repository.save(fetchedBoardGameV2);

                    System.out.println("Saving or Updating boardgame info for gameId = " + gameId);
                }
            });
        }
    }

    @SneakyThrows
    @Override
    public void refreshGameInfo(int gameId) {
        GameInfoRequest request = new GameInfoRequest();
        request.setGameId(gameId);

        String json = JsonUtil.convertToString(request);

        URL obj = null;
        try {
            obj = new URL(GAME_INFO_GET_URL);
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

        if (fetchedBoardGameV2 == null) {
            fetchedBoardGameV2 = new BoardGameV2();
        }

        if (gameInfoResponse.getData() != null && gameInfoResponse.getData().getGame_info() != null) {
            Game game = gameInfoResponse.getData().getGame_info();

            fetchedBoardGameV2.setGameId(gameId);
            fetchedBoardGameV2.setTotalTime(game.getTotalTime());
            fetchedBoardGameV2.setAverageTimePerPlayer(game.getAverageTimePerPlayer());
            fetchedBoardGameV2.setPrimaryLanguage(game.getPrimaryLanguage());

            fetchedBoardGameV2.setExpansionType(game.getExpansionType());
            fetchedBoardGameV2.setIsExpansion(game.getIsExpansion() == 1);

            // same game
            fetchedBoardGameV2.setEnglishName(game.getEnglishName());

            if (StringUtils.isBlank(game.getChineseName())) {
                fetchedBoardGameV2.setChineseName(game.getEnglishName());
            } else {
                fetchedBoardGameV2.setChineseName(game.getChineseName());
            }

            fetchedBoardGameV2.setEnglishDescription(game.getEnglishDescription());

            if (StringUtils.isBlank(game.getChineseDescription())) {
                fetchedBoardGameV2.setChineseDescription(game.getEnglishDescription());
            } else {
                fetchedBoardGameV2.setChineseDescription(game.getChineseDescription());
            }

            List<Category> categoryList = game.getCategory();
            Set<String> categories = categoryList.stream().map(Category::getValue).collect(Collectors.toSet());
            fetchedBoardGameV2.setCategories(categories);

            List<Theme> themeList = game.getTheme();
            Set<String> themes = themeList.stream().map(Theme::getValue).collect(Collectors.toSet());
            fetchedBoardGameV2.setThemes(themes);

            fetchedBoardGameV2.setMode(game.getMode().getValue());

            fetchedBoardGameV2.setBoxUrl(game.getBoxUrl());
            fetchedBoardGameV2.setCoverUrl(game.getCoverUrl());
            fetchedBoardGameV2.setStatus(game.getStatus().getValue());
            fetchedBoardGameV2.setDifficulty(game.getDifficulty());
            fetchedBoardGameV2.setPublishYear(game.getPublishYear());

            fetchedBoardGameV2.setBggId(game.getBggId());

            if (game.getRelationInfo() != null) {
                RelationInfo relationInfo = game.getRelationInfo();
                fetchedBoardGameV2.setRelationGameIds(relationInfo.getRelationGameIds());
                fetchedBoardGameV2.setRelationGameIndices(relationInfo.getRelationIndices());
            }

            List<Integer> playerNums = game.getPlayerNum();
            fetchedBoardGameV2.setPlayerNums(playerNums);

            MinMaxPlayer minMaxPlayer = calcMinMaxPlayerCount(playerNums);

            fetchedBoardGameV2.setMinPlayers(minMaxPlayer.minPlayers());
            fetchedBoardGameV2.setMaxPlayers(minMaxPlayer.maxPlayers());

            fetchedBoardGameV2.setGameHotnessValue(game.getGameHotnessValue());
            fetchedBoardGameV2.setWemeetRating(0);
            fetchedBoardGameV2.setGstoneRating(game.getGstoneRating());

            fetchedBoardGameV2.setCreatedAt(LocalDate.now());
            fetchedBoardGameV2.setUpdatedAt(LocalDate.now());

            boardGameV2Repository.save(fetchedBoardGameV2);

            System.out.println("Refresh boardgame info for gameId = " + gameId);
        }
    }

    private static MinMaxPlayer calcMinMaxPlayerCount(List<Integer> playerNums) {
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

        return new MinMaxPlayer(minPlayers, maxPlayers);
    }

    private record MinMaxPlayer(int minPlayers, int maxPlayers) {}
}

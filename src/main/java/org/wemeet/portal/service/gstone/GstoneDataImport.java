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
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.wemeet.portal.domain.BoardGameV2;
import org.wemeet.portal.domain.RelationGameIndex;
import org.wemeet.portal.model.gstone.*;
import org.wemeet.portal.model.gstone.v2.*;
import org.wemeet.portal.model.gstone.v2.Category;
import org.wemeet.portal.model.gstone.v2.GameInfo;
import org.wemeet.portal.model.gstone.v2.Theme;
import org.wemeet.portal.repository.BoardGameV2Repository;
import org.wemeet.portal.util.JsonUtil;

@Service
@Slf4j
@RequiredArgsConstructor
public class GstoneDataImport {

    private final BoardGameV2Repository boardGameV2Repository;

    @SneakyThrows
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
                    log.info("skipping existing game {} - {}", gameId, game.getChineseName());
                    continue;
                }

                BoardGameV2 boardGameV2 = new BoardGameV2();

                boardGameV2.setGameId(gameId);
                boardGameV2.setEnglishName(game.getEnglishName());
                boardGameV2.setChineseName(game.getChineseName());
                boardGameV2.setTotalTime(game.getTotalTime());
                boardGameV2.setAverageTimePerPlayer(game.getAverageTimePerPlayer());
                boardGameV2.setPrimaryLanguage(game.getPrimaryLanguage());

                List<org.wemeet.portal.model.gstone.Category> categoryList = game.getCategory();
                Set<String> categories = categoryList
                    .stream()
                    .map(org.wemeet.portal.model.gstone.Category::getValue)
                    .collect(Collectors.toSet());
                boardGameV2.setCategories(categories);

                List<org.wemeet.portal.model.gstone.Theme> themeList = game.getTheme();
                Set<String> themes = themeList.stream().map(org.wemeet.portal.model.gstone.Theme::getValue).collect(Collectors.toSet());
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

        /**
         *         List<BoardGameV2> boardGameV2List = new ArrayList<>();
         *         BoardGameV2 gameV2 = new BoardGameV2();
         *         gameV2.setGameId(29568);
         *         boardGameV2List.add(gameV2);
         */

        String url = "https://www.gstonegames.com/app/v2/game_info_get/";

        int[] numbers = {
            3565,
            26293,
            2012,
            6542,
            551,
            12280,
            32927,
            22327,
            38555,
            22645,
            12277,
            46573,
            12272,
            41094,
            12269,
            42888,
            41629,
            19005,
            12270,
            12271,
            24951,
            12273,
            12279,
            12275,
            41616,
            41618,
            41621,
            41613,
            42887,
            41622,
            41614,
            41612,
            41615,
            41624,
            41610,
            41611,
            41623,
            41625,
            41626,
            41627,
            41628,
            41631,
            42885,
            42886,
            41617,
            41619,
            41620,
            41630,
        };

        for (int gameId : numbers) {
            BoardGameV2 existingGame = boardGameV2Repository.findGameById(gameId);

            if (existingGame != null) {
                log.info("skipping existing game {} - {}", gameId, existingGame.getEnglishName());
                continue;
            }

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
            } catch (Exception e) {
                log.error("Error reading response for gameId {}: {}", gameId, e.getMessage());
            }

            GstoneGameInfoResponseV2 gstoneGameInfoResponseV2 = JsonUtil.convertToObject(
                response.toString(),
                GstoneGameInfoResponseV2.class
            );
            BoardGameV2 boardGameV2 = new BoardGameV2();

            if (gstoneGameInfoResponseV2.getData() != null && gstoneGameInfoResponseV2.getData().getGameInfo() != null) {
                GameInfo game = gstoneGameInfoResponseV2.getData().getGameInfo();

                boardGameV2.setGameId(gameId);
                boardGameV2.setEnglishName(game.getpName());
                boardGameV2.setChineseName(game.getName());

                boardGameV2.setEnglishDescription(game.getpDescription());
                boardGameV2.setChineseDescription(game.getDescription());

                boardGameV2.setBggId(game.getBggId());
                boardGameV2.setRelationGameIds(game.getRelationInfo().getRelationGameIds());
                boardGameV2.setIsExpansion(game.getIsExpansion() == 1);
                boardGameV2.setExpansionType(game.getExpansionType());

                List<RelationGameIndex> relationGameIndices = new ArrayList<>();
                List<RelationIndexL> relationIndexList = game.getRelationInfo().getRelationIndexLs();

                for (RelationIndexL relationIndex : relationIndexList) {
                    RelationGameIndex relationGameIndex = new RelationGameIndex();

                    relationGameIndex.setIs_mm(relationIndex.getIsMm());
                    relationGameIndex.setSub_category(relationIndex.getSubCategory());
                    relationGameIndex.setIs_expansion(relationIndex.getIsExpansion());
                    relationGameIndex.setMod_type(relationIndex.getModType());
                    relationGameIndex.setIs_pg(relationIndex.getIsPg());
                    relationGameIndex.setBox_url(relationIndex.getBoxUrl());
                    relationGameIndex.setPrimary_language(relationIndex.getPrimaryLanguage());
                    relationGameIndex.setId(relationIndex.getId());
                    relationGameIndex.setCover_url_s(relationIndex.getCoverUrlS());
                    relationGameIndex.setPublish_year(relationIndex.getPublishYear());
                    relationGameIndex.setStatus(relationIndex.getStatus());
                    relationGameIndex.setExpansion_type(relationIndex.getExpansionType());
                    relationGameIndex.setWidth_height(relationIndex.getWidthHeight());
                    relationGameIndex.setIs_glight(relationIndex.getIsGlight());
                    relationGameIndex.setCover_url(relationIndex.getCoverUrl());
                    relationGameIndex.setFirst_picture(relationIndex.getFirstPicture());
                    relationGameIndex.setName(relationIndex.getName());
                    relationGameIndex.setGame_hotness_value(relationIndex.getGameHotnessValue());
                    relationGameIndex.setSales_mode_id(relationIndex.getSalesModeId());
                    relationGameIndex.setBox_url_s(relationIndex.getBoxUrlS());
                    relationGameIndex.setPublish_month(relationIndex.getPublishMonth());

                    relationGameIndices.add(relationGameIndex);
                }

                boardGameV2.setRelationGameIndices(relationGameIndices);

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

                log.info("GameId {} - {} saved successfully", gameId, boardGameV2.getChineseName());
            }
        }
    }
}

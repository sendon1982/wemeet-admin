package org.wemeet.portal.service.gstone;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.wemeet.portal.domain.BoardGameV2;
import org.wemeet.portal.model.gstone.*;
import org.wemeet.portal.repository.BoardGameV2Repository;
import org.wemeet.portal.util.JsonUtil;

@Service
@Slf4j
@RequiredArgsConstructor
public class GstoneServiceImpl implements GstoneService {

    private final RestTemplate restTemplate;

    private final BoardGameV2Repository boardGameV2Repository;

    @Override
    public List<BoardGameV2> searchGames(
        String englishName,
        String chineseName,
        Integer minPlayers,
        Integer maxPlayers,
        String category,
        String theme
    ) {
        if (englishName != null) {
            return boardGameV2Repository.findByEnglishNameContainingIgnoreCase(englishName);
        }
        if (chineseName != null) {
            return boardGameV2Repository.findByChineseNameContainingIgnoreCase(chineseName);
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

        return boardGameV2Repository.findAll();
    }

    @SneakyThrows
    @Override
    public GstoneResponse getRankList() {
        String url = "https://www.gstonegames.com/app/v2/rank_list_get/";

        for (int k = 1; k <= 13; k++) {
            GstoneRequest request = new GstoneRequest();
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

            for (Game game : gstoneResponse.getData().getGameList()) {
                BoardGameV2 boardGameV2 = new BoardGameV2();

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
                //boardGameV2Repository.save(boardGameV2);
            }

            Thread.sleep(5000);
        }

        return null;
    }
}

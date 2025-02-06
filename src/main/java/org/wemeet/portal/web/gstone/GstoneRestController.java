package org.wemeet.portal.web.gstone;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wemeet.portal.domain.BoardGameV2;
import org.wemeet.portal.gen.service.gstone.BoardGameApi;
import org.wemeet.portal.gen.service.model.Game;
import org.wemeet.portal.service.gstone.GstoneService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/boardgames")
public class GstoneRestController implements BoardGameApi {

    private final GstoneService gstoneService;

    @Override
    public ResponseEntity<List<Game>> gamesSearchGet(
        String englishName,
        String chineseName,
        Integer minPlayers,
        Integer maxPlayers,
        String category,
        String theme
    ) {
        List<BoardGameV2> boardGameV2List = gstoneService.searchGames(englishName, chineseName, minPlayers, maxPlayers, category, theme);

        List<Game> games = new ArrayList<>();
        for (BoardGameV2 boardGameV2 : boardGameV2List) {
            Game game = new Game();

            game.setId(boardGameV2.getId());
            game.setEnglishName(boardGameV2.getEnglishName());
            game.setChineseName(boardGameV2.getChineseName());
            game.setTotalTime(boardGameV2.getTotalTime());
            game.setAverageTimePerPlayer(boardGameV2.getAverageTimePerPlayer());
            game.setPrimaryLanguage(boardGameV2.getPrimaryLanguage());
            game.setCategories(new ArrayList<>(boardGameV2.getCategories()));
            game.setThemes(new ArrayList<>(boardGameV2.getThemes()));
            game.setMode(boardGameV2.getMode());
            game.setBoxUrl(boardGameV2.getBoxUrl());
            game.setCoverUrl(boardGameV2.getCoverUrl());
            game.setStatus(boardGameV2.getStatus());
            game.setDifficulty(boardGameV2.getDifficulty());
            game.setPublishYear(boardGameV2.getPublishYear());
            game.setMinPlayers(boardGameV2.getMinPlayers());
            game.setMaxPlayers(boardGameV2.getMaxPlayers());
            game.setGameHotnessValue(Double.valueOf(boardGameV2.getGameHotnessValue()).intValue());
            game.setWemeetRating(BigDecimal.valueOf(boardGameV2.getWemeetRating()));
            game.setGstoneRating(BigDecimal.valueOf(boardGameV2.getGstoneRating()));

            games.add(game);
        }

        return ResponseEntity.ok(games);
    }
}

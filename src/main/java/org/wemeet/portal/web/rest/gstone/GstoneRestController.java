package org.wemeet.portal.web.rest.gstone;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wemeet.portal.domain.BoardGameV2;
import org.wemeet.portal.domain.RelationGameIndex;
import org.wemeet.portal.gen.service.gstone.BoardGameApi;
import org.wemeet.portal.gen.service.model.Game;
import org.wemeet.portal.gen.service.model.GameQueryRequest;
import org.wemeet.portal.gen.service.model.RelationGame;
import org.wemeet.portal.gen.service.model.RelationGameFirstPicture;
import org.wemeet.portal.service.gstone.GstoneService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/boardgames")
public class GstoneRestController implements BoardGameApi {

    private final GstoneService gstoneService;

    @Override
    public ResponseEntity<Game> getGameById(Integer gameId, Boolean includeExpansion) {
        BoardGameV2 boardGameV2 = gstoneService.findGameById(gameId, includeExpansion);
        Game game = toGame(boardGameV2);

        return ResponseEntity.ok(game);
    }

    @Override
    public ResponseEntity<List<Game>> getGameListByIds(String gameIds) {
        if (StringUtils.isBlank(gameIds)) {
            return ResponseEntity.ok().build();
        }

        String[] idArrays = StringUtils.trim(gameIds).split(",");
        Set<Integer> gameIdSet = new HashSet<>();

        for (String strId : idArrays) {
            if (StringUtils.isNumeric(strId)) {
                gameIdSet.add(Integer.parseInt(strId));
            }
        }

        List<BoardGameV2> boardGameV2List = gstoneService.getGameListByIds(new ArrayList<>(gameIdSet));

        List<Game> games = new ArrayList<>();
        for (BoardGameV2 boardGameV2 : boardGameV2List) {
            Game game = toGame(boardGameV2);

            games.add(game);
        }

        return ResponseEntity.ok(games);
    }

    @Override
    public ResponseEntity<List<Game>> searchBoardGames(String name, Integer minPlayers, Integer maxPlayers, String category, String theme) {
        List<BoardGameV2> boardGameV2List = gstoneService.searchGames(name, minPlayers, maxPlayers, category, theme);

        List<Game> games = new ArrayList<>();
        for (BoardGameV2 boardGameV2 : boardGameV2List) {
            Game game = toGame(boardGameV2);

            games.add(game);
        }

        return ResponseEntity.ok(games);
    }

    private static Game toGame(BoardGameV2 boardGameV2) {
        Game game = new Game();

        game.setId(boardGameV2.getId());
        game.setEnglishName(boardGameV2.getEnglishName());
        game.setChineseName(boardGameV2.getChineseName());
        game.setGameId(boardGameV2.getGameId());
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

        List<RelationGame> relationGames = toRelationGames(boardGameV2.getRelationGameIndices());
        game.setRelationInfo(relationGames);

        return game;
    }

    private static List<RelationGame> toRelationGames(List<RelationGameIndex> relationGameIndices) {
        List<RelationGame> result = new ArrayList<>();

        for (RelationGameIndex gameIndex : relationGameIndices) {
            RelationGame relationGame = new RelationGame();
            relationGame.setIsMm(gameIndex.is_mm);
            relationGame.setSubCategory(gameIndex.sub_category);
            relationGame.setIsExpansion(gameIndex.is_expansion);
            relationGame.setModType(gameIndex.expansion_type);
            relationGame.setIsPg(gameIndex.is_pg);
            relationGame.setBoxUrl(gameIndex.box_url);
            relationGame.setPrimaryLanguage(gameIndex.primary_language);
            relationGame.setId(gameIndex.getId());
            relationGame.setCoverUrlS(gameIndex.cover_url_s);
            relationGame.setPublishYear(gameIndex.publish_year);
            relationGame.setStatus(gameIndex.status);
            relationGame.setExpansionType(gameIndex.expansion_type);
            relationGame.setWidthHeight(gameIndex.width_height);
            relationGame.setIsGlight(gameIndex.is_glight);
            relationGame.setCoverUrl(gameIndex.cover_url);

            RelationGameFirstPicture firstPicture = new RelationGameFirstPicture();
            firstPicture.setPictureUrl(gameIndex.first_picture.picture_url);
            firstPicture.setPictureUrlS(gameIndex.first_picture.picture_url_s);
            firstPicture.setWidthHeight(gameIndex.first_picture.width_height);
            relationGame.setFirstPicture(firstPicture);

            relationGame.setName(gameIndex.name);
            relationGame.setGameHotnessValue(gameIndex.game_hotness_value);
            relationGame.setSalesModeId(gameIndex.sales_mode_id);
            relationGame.setBoxUrlS(gameIndex.box_url_s);
            relationGame.setPublishMonth(gameIndex.publish_month);

            result.add(relationGame);
        }

        return result;
    }
}

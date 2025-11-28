package org.wemeet.portal.web.rest.gstone;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wemeet.portal.domain.BoardGameV2;
import org.wemeet.portal.domain.RelationGameIndex;
import org.wemeet.portal.gen.service.gstone.BoardGameApi;
import org.wemeet.portal.gen.service.model.*;
import org.wemeet.portal.service.gstone.GstoneService;
import org.wemeet.portal.util.GameUtil;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/boardgames")
public class GstoneRestController implements BoardGameApi {

    private final GstoneService gstoneService;

    @Override
    public ResponseEntity<Game> getGameById(Integer gameId, Boolean includeExpansion) {
        BoardGameV2 boardGameV2 = gstoneService.findGameById(gameId, includeExpansion);

        Game game = toGame(boardGameV2, false);

        List<RelationGameIndex> relationGameIndices = boardGameV2.getRelationGameIndices();
        Set<Integer> expansionGameIds = GameUtil.filterExpansionGameIds(relationGameIndices);
        List<BoardGameV2> expansionBoardGameList = gstoneService.getGameListByIds(new ArrayList<>(expansionGameIds));
        List<ExpansionGame> expansionGames = toExpansionGame(expansionBoardGameList);

        game.setExpansionGames(expansionGames);

        return ResponseEntity.ok(game);
    }

    /**
     * Find game list by its gameId
     * * <p><b>200</b> - A board game information
     * @param gameIds ID of games and separated by comma (optional)
     * @return List&lt;Game&gt;
     */
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

        // Fetch the main list of BoardGameV2 objects
        List<BoardGameV2> boardGameV2List = gstoneService.getGameListByIds(new ArrayList<>(gameIdSet));
        List<Game> games = new ArrayList<>();

        if (boardGameV2List != null && !boardGameV2List.isEmpty()) {
            // Step 1: Collect all unique expansion game IDs from all BoardGameV2 objects
            Set<Integer> allUniqueExpansionGameIds = new HashSet<>();
            for (BoardGameV2 game : boardGameV2List) {
                List<RelationGameIndex> relationGameIndices = game.getRelationGameIndices();
                if (relationGameIndices != null) {
                    Set<Integer> gameExpansionIds = GameUtil.filterExpansionGameIds(relationGameIndices);
                    allUniqueExpansionGameIds.addAll(gameExpansionIds);
                }
            }

            // Step 2: Fetch all necessary expansion BoardGameV2 objects in a single call
            List<BoardGameV2> fetchedExpansionBoardGameList = Collections.emptyList();
            if (!allUniqueExpansionGameIds.isEmpty()) {
                fetchedExpansionBoardGameList = gstoneService.getGameListByIds(new ArrayList<>(allUniqueExpansionGameIds));
            }

            // Step 3: Create a map of ExpansionGame ID to ExpansionGame object for efficient lookup
            Map<Integer, ExpansionGame> expansionGameMap = fetchedExpansionBoardGameList
                .stream()
                .map(GstoneRestController::toExpansionGame) // Convert BoardGameV2 to ExpansionGame
                .collect(Collectors.toMap(ExpansionGame::getGameId, Function.identity())); // Map by ExpansionGame ID

            // Step 4: Iterate through the original list and set the expansion games
            for (BoardGameV2 boardGameV2 : boardGameV2List) {
                List<RelationGameIndex> relationGameIndices = boardGameV2.getRelationGameIndices();
                Game game = toGame(boardGameV2, false);

                if (relationGameIndices != null) {
                    Set<Integer> gameExpansionIds = GameUtil.filterExpansionGameIds(relationGameIndices);

                    List<ExpansionGame> currentExpansionGames = new ArrayList<>();
                    for (Integer expansionId : gameExpansionIds) {
                        ExpansionGame expansionGame = expansionGameMap.get(expansionId);
                        if (expansionGame != null) {
                            currentExpansionGames.add(expansionGame);
                        }
                    }

                    game.setExpansionGames(currentExpansionGames);
                    games.add(game);
                } else {
                    game.setExpansionGames(new ArrayList<>());
                }
            }
        }

        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Game>> searchBoardGames(
        String name,
        Integer minPlayers,
        Integer maxPlayers,
        String category,
        String theme,
        Boolean includeRelatedGames
    ) {
        List<BoardGameV2> originalBoardGameV2List = gstoneService.searchGames(name, minPlayers, maxPlayers, category, theme);
        List<BoardGameV2> boardGameV2List = originalBoardGameV2List.stream().filter(boardGameV2 -> !boardGameV2.getIsExpansion()).toList();

        List<Game> games = new ArrayList<>();
        for (BoardGameV2 boardGameV2 : boardGameV2List) {
            List<RelationGameIndex> relationGameIndices = boardGameV2.getRelationGameIndices();
            Set<Integer> expansionGameIds = GameUtil.filterExpansionGameIds(relationGameIndices);

            List<BoardGameV2> expansionBoardGameList = gstoneService.getGameListByIds(new ArrayList<>(expansionGameIds));
            List<ExpansionGame> expansionGames = toExpansionGame(expansionBoardGameList);

            Game game = toGame(boardGameV2, false);
            game.setExpansionGames(expansionGames);

            games.add(game);
        }

        return ResponseEntity.ok(games);
    }

    private static Game toGame(BoardGameV2 boardGameV2, Boolean includeRelatedGames) {
        Game game = new Game();

        game.setId(boardGameV2.getId());
        game.setEnglishName(boardGameV2.getEnglishName());
        game.setChineseName(boardGameV2.getChineseName());
        game.setEnglishDescription(boardGameV2.getEnglishDescription());
        game.setChineseDescription(boardGameV2.getChineseDescription());

        game.setGameId(boardGameV2.getGameId());
        game.setIsExpansion(boardGameV2.getIsExpansion());

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

        if (BooleanUtils.isTrue(includeRelatedGames)) {
            List<RelationGame> relationGames = toRelationGames(boardGameV2.getRelationGameIndices());
            game.setRelationInfo(relationGames);
        }

        return game;
    }

    private static List<ExpansionGame> toExpansionGame(List<BoardGameV2> boardGameV2List) {
        List<ExpansionGame> expansionGameList = new ArrayList<>();
        for (BoardGameV2 boardGameV2 : boardGameV2List) {
            expansionGameList.add(toExpansionGame(boardGameV2));
        }

        return expansionGameList;
    }

    private static ExpansionGame toExpansionGame(BoardGameV2 boardGameV2) {
        ExpansionGame game = new ExpansionGame();

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

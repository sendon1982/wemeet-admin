package org.wemeet.portal.repository;

import java.util.List;
import org.wemeet.portal.domain.BoardGameV2;

/**
 * Spring Data MongoDB repository for the BoardGame entity.
 */
public interface BoardGameV2Repository {
    BoardGameV2 findGameById(int gameId);

    List<BoardGameV2> getGameListByIds(List<Integer> gameIds);

    List<BoardGameV2> findByName(String chineseName);

    List<BoardGameV2> findByEnglishNameContainingIgnoreCase(String englishName);

    List<BoardGameV2> findByChineseNameContainingIgnoreCase(String chineseName);

    List<BoardGameV2> findByMinPlayersLessThanEqualAndMaxPlayersGreaterThanEqual(int minPlayers, int maxPlayers);

    List<BoardGameV2> findByCategoriesContaining(String category);

    List<BoardGameV2> findByThemesContaining(String theme);

    List<BoardGameV2> findAll();

    void save(BoardGameV2 boardGameV2);
}

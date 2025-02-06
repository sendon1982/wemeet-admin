package org.wemeet.portal.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.wemeet.portal.domain.BoardGame;
import org.wemeet.portal.domain.BoardGameV2;

/**
 * Spring Data MongoDB repository for the BoardGame entity.
 */
public interface BoardGameV2Repository {
    List<BoardGameV2> findByEnglishNameContainingIgnoreCase(String englishName);

    List<BoardGameV2> findByChineseNameContainingIgnoreCase(String chineseName);

    List<BoardGameV2> findByMinPlayersLessThanEqualAndMaxPlayersGreaterThanEqual(int minPlayers, int maxPlayers);

    List<BoardGameV2> findByCategoriesContaining(String category);

    List<BoardGameV2> findByThemesContaining(String theme);

    List<BoardGameV2> findAll();
}

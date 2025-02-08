package org.wemeet.portal.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.wemeet.portal.domain.BoardGameV2;

@Repository
@Slf4j
@RequiredArgsConstructor
public class BoardGameV2RepositoryImpl implements BoardGameV2Repository {

    private final MongoTemplate mongoTemplate;

    @Override
    public BoardGameV2 findGameById(int gameId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("gameId").is(gameId));

        return mongoTemplate.findOne(query, BoardGameV2.class);
    }

    @Override
    public List<BoardGameV2> getGameListByIds(List<Integer> gameIds) {
        Query query = new Query();
        query.addCriteria(Criteria.where("gameId").in(gameIds));

        return mongoTemplate.find(query, BoardGameV2.class);
    }

    @Override
    public List<BoardGameV2> findByChineseName(String chineseName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("chineseName").is(chineseName));

        return mongoTemplate.find(query, BoardGameV2.class);
    }

    @Override
    public List<BoardGameV2> findByEnglishNameContainingIgnoreCase(String englishName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("englishName").regex(englishName, "i"));

        return mongoTemplate.find(query, BoardGameV2.class);
    }

    @Override
    public List<BoardGameV2> findByChineseNameContainingIgnoreCase(String chineseName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("chineseName").regex(chineseName, "i"));

        return mongoTemplate.find(query, BoardGameV2.class);
    }

    @Override
    public List<BoardGameV2> findByMinPlayersLessThanEqualAndMaxPlayersGreaterThanEqual(int minPlayers, int maxPlayers) {
        Query query = new Query();
        query.addCriteria(Criteria.where("minPlayers").lte(minPlayers).and("maxPlayers").gte(maxPlayers));

        return mongoTemplate.find(query, BoardGameV2.class);
    }

    @Override
    public List<BoardGameV2> findByCategoriesContaining(String category) {
        Query query = new Query();
        query.addCriteria(Criteria.where("categories").in(category));

        return mongoTemplate.find(query, BoardGameV2.class);
    }

    @Override
    public List<BoardGameV2> findByThemesContaining(String theme) {
        Query query = new Query();
        query.addCriteria(Criteria.where("themes").in(theme));

        return mongoTemplate.find(query, BoardGameV2.class);
    }

    @Override
    public List<BoardGameV2> findAll() {
        return mongoTemplate.findAll(BoardGameV2.class);
    }

    @Override
    public void save(BoardGameV2 boardGameV2) {
        mongoTemplate.save(boardGameV2);
    }
}

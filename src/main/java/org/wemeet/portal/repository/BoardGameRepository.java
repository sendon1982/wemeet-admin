package org.wemeet.portal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.wemeet.portal.domain.BoardGame;

/**
 * Spring Data MongoDB repository for the BoardGame entity.
 */
@Repository
public interface BoardGameRepository extends MongoRepository<BoardGame, String> {}

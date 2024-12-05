package org.wemeet.portal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.wemeet.portal.domain.Game;

/**
 * Spring Data MongoDB repository for the Game entity.
 */
@Repository
public interface GameRepository extends MongoRepository<Game, String> {}

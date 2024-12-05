package org.wemeet.portal.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wemeet.portal.service.dto.GameDTO;

/**
 * Service Interface for managing {@link org.wemeet.portal.domain.Game}.
 */
public interface GameService {
    /**
     * Save a game.
     *
     * @param gameDTO the entity to save.
     * @return the persisted entity.
     */
    GameDTO save(GameDTO gameDTO);

    /**
     * Updates a game.
     *
     * @param gameDTO the entity to update.
     * @return the persisted entity.
     */
    GameDTO update(GameDTO gameDTO);

    /**
     * Partially updates a game.
     *
     * @param gameDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<GameDTO> partialUpdate(GameDTO gameDTO);

    /**
     * Get all the games.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GameDTO> findAll(Pageable pageable);

    /**
     * Get the "id" game.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GameDTO> findOne(String id);

    /**
     * Delete the "id" game.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}

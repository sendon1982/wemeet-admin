package org.wemeet.portal.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wemeet.portal.service.dto.BoardGameDTO;

/**
 * Service Interface for managing {@link org.wemeet.portal.domain.BoardGame}.
 */
public interface BoardGameService {
    /**
     * Save a boardGame.
     *
     * @param boardGameDTO the entity to save.
     * @return the persisted entity.
     */
    BoardGameDTO save(BoardGameDTO boardGameDTO);

    /**
     * Updates a boardGame.
     *
     * @param boardGameDTO the entity to update.
     * @return the persisted entity.
     */
    BoardGameDTO update(BoardGameDTO boardGameDTO);

    /**
     * Partially updates a boardGame.
     *
     * @param boardGameDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BoardGameDTO> partialUpdate(BoardGameDTO boardGameDTO);

    /**
     * Get all the boardGames.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BoardGameDTO> findAll(Pageable pageable);

    /**
     * Get the "id" boardGame.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BoardGameDTO> findOne(String id);

    /**
     * Delete the "id" boardGame.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}

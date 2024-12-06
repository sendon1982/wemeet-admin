package org.wemeet.portal.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.wemeet.portal.repository.BoardGameRepository;
import org.wemeet.portal.service.BoardGameService;
import org.wemeet.portal.service.dto.BoardGameDTO;
import org.wemeet.portal.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.wemeet.portal.domain.BoardGame}.
 */
@RestController
@RequestMapping("/api/board-games")
public class BoardGameResource {

    private static final Logger LOG = LoggerFactory.getLogger(BoardGameResource.class);

    private static final String ENTITY_NAME = "boardGame";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BoardGameService boardGameService;

    private final BoardGameRepository boardGameRepository;

    public BoardGameResource(BoardGameService boardGameService, BoardGameRepository boardGameRepository) {
        this.boardGameService = boardGameService;
        this.boardGameRepository = boardGameRepository;
    }

    /**
     * {@code POST  /board-games} : Create a new boardGame.
     *
     * @param boardGameDTO the boardGameDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new boardGameDTO, or with status {@code 400 (Bad Request)} if the boardGame has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<BoardGameDTO> createBoardGame(@Valid @RequestBody BoardGameDTO boardGameDTO) throws URISyntaxException {
        LOG.debug("REST request to save BoardGame : {}", boardGameDTO);
        if (boardGameDTO.getId() != null) {
            throw new BadRequestAlertException("A new boardGame cannot already have an ID", ENTITY_NAME, "idexists");
        }
        boardGameDTO = boardGameService.save(boardGameDTO);
        return ResponseEntity.created(new URI("/api/board-games/" + boardGameDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, boardGameDTO.getId()))
            .body(boardGameDTO);
    }

    /**
     * {@code PUT  /board-games/:id} : Updates an existing boardGame.
     *
     * @param id the id of the boardGameDTO to save.
     * @param boardGameDTO the boardGameDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated boardGameDTO,
     * or with status {@code 400 (Bad Request)} if the boardGameDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the boardGameDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BoardGameDTO> updateBoardGame(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody BoardGameDTO boardGameDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update BoardGame : {}, {}", id, boardGameDTO);
        if (boardGameDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, boardGameDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!boardGameRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        boardGameDTO = boardGameService.update(boardGameDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, boardGameDTO.getId()))
            .body(boardGameDTO);
    }

    /**
     * {@code PATCH  /board-games/:id} : Partial updates given fields of an existing boardGame, field will ignore if it is null
     *
     * @param id the id of the boardGameDTO to save.
     * @param boardGameDTO the boardGameDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated boardGameDTO,
     * or with status {@code 400 (Bad Request)} if the boardGameDTO is not valid,
     * or with status {@code 404 (Not Found)} if the boardGameDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the boardGameDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BoardGameDTO> partialUpdateBoardGame(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody BoardGameDTO boardGameDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update BoardGame partially : {}, {}", id, boardGameDTO);
        if (boardGameDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, boardGameDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!boardGameRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BoardGameDTO> result = boardGameService.partialUpdate(boardGameDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, boardGameDTO.getId())
        );
    }

    /**
     * {@code GET  /board-games} : get all the boardGames.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of boardGames in body.
     */
    @GetMapping("")
    public ResponseEntity<List<BoardGameDTO>> getAllBoardGames(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        LOG.debug("REST request to get a page of BoardGames");
        Page<BoardGameDTO> page = boardGameService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /board-games/:id} : get the "id" boardGame.
     *
     * @param id the id of the boardGameDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the boardGameDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BoardGameDTO> getBoardGame(@PathVariable("id") String id) {
        LOG.debug("REST request to get BoardGame : {}", id);
        Optional<BoardGameDTO> boardGameDTO = boardGameService.findOne(id);
        return ResponseUtil.wrapOrNotFound(boardGameDTO);
    }

    /**
     * {@code DELETE  /board-games/:id} : delete the "id" boardGame.
     *
     * @param id the id of the boardGameDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoardGame(@PathVariable("id") String id) {
        LOG.debug("REST request to delete BoardGame : {}", id);
        boardGameService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}

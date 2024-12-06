package org.wemeet.portal.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wemeet.portal.domain.BoardGame;
import org.wemeet.portal.repository.BoardGameRepository;
import org.wemeet.portal.service.BoardGameService;
import org.wemeet.portal.service.dto.BoardGameDTO;
import org.wemeet.portal.service.mapper.BoardGameMapper;

/**
 * Service Implementation for managing {@link org.wemeet.portal.domain.BoardGame}.
 */
@Service
public class BoardGameServiceImpl implements BoardGameService {

    private static final Logger LOG = LoggerFactory.getLogger(BoardGameServiceImpl.class);

    private final BoardGameRepository boardGameRepository;

    private final BoardGameMapper boardGameMapper;

    public BoardGameServiceImpl(BoardGameRepository boardGameRepository, BoardGameMapper boardGameMapper) {
        this.boardGameRepository = boardGameRepository;
        this.boardGameMapper = boardGameMapper;
    }

    @Override
    public BoardGameDTO save(BoardGameDTO boardGameDTO) {
        LOG.debug("Request to save BoardGame : {}", boardGameDTO);
        BoardGame boardGame = boardGameMapper.toEntity(boardGameDTO);
        boardGame = boardGameRepository.save(boardGame);
        return boardGameMapper.toDto(boardGame);
    }

    @Override
    public BoardGameDTO update(BoardGameDTO boardGameDTO) {
        LOG.debug("Request to update BoardGame : {}", boardGameDTO);
        BoardGame boardGame = boardGameMapper.toEntity(boardGameDTO);
        boardGame = boardGameRepository.save(boardGame);
        return boardGameMapper.toDto(boardGame);
    }

    @Override
    public Optional<BoardGameDTO> partialUpdate(BoardGameDTO boardGameDTO) {
        LOG.debug("Request to partially update BoardGame : {}", boardGameDTO);

        return boardGameRepository
            .findById(boardGameDTO.getId())
            .map(existingBoardGame -> {
                boardGameMapper.partialUpdate(existingBoardGame, boardGameDTO);

                return existingBoardGame;
            })
            .map(boardGameRepository::save)
            .map(boardGameMapper::toDto);
    }

    @Override
    public Page<BoardGameDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all BoardGames");
        return boardGameRepository.findAll(pageable).map(boardGameMapper::toDto);
    }

    @Override
    public Optional<BoardGameDTO> findOne(String id) {
        LOG.debug("Request to get BoardGame : {}", id);
        return boardGameRepository.findById(id).map(boardGameMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete BoardGame : {}", id);
        boardGameRepository.deleteById(id);
    }
}

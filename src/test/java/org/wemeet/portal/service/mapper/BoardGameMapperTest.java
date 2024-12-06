package org.wemeet.portal.service.mapper;

import static org.wemeet.portal.domain.BoardGameAsserts.*;
import static org.wemeet.portal.domain.BoardGameTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardGameMapperTest {

    private BoardGameMapper boardGameMapper;

    @BeforeEach
    void setUp() {
        boardGameMapper = new BoardGameMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getBoardGameSample1();
        var actual = boardGameMapper.toEntity(boardGameMapper.toDto(expected));
        assertBoardGameAllPropertiesEquals(expected, actual);
    }
}

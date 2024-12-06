package org.wemeet.portal.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.wemeet.portal.domain.BoardGameTestSamples.*;

import org.junit.jupiter.api.Test;
import org.wemeet.portal.web.rest.TestUtil;

class BoardGameTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BoardGame.class);
        BoardGame boardGame1 = getBoardGameSample1();
        BoardGame boardGame2 = new BoardGame();
        assertThat(boardGame1).isNotEqualTo(boardGame2);

        boardGame2.setId(boardGame1.getId());
        assertThat(boardGame1).isEqualTo(boardGame2);

        boardGame2 = getBoardGameSample2();
        assertThat(boardGame1).isNotEqualTo(boardGame2);
    }
}

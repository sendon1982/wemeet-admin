package org.wemeet.portal.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.wemeet.portal.web.rest.TestUtil;

class BoardGameDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BoardGameDTO.class);
        BoardGameDTO boardGameDTO1 = new BoardGameDTO();
        boardGameDTO1.setId("id1");
        BoardGameDTO boardGameDTO2 = new BoardGameDTO();
        assertThat(boardGameDTO1).isNotEqualTo(boardGameDTO2);
        boardGameDTO2.setId(boardGameDTO1.getId());
        assertThat(boardGameDTO1).isEqualTo(boardGameDTO2);
        boardGameDTO2.setId("id2");
        assertThat(boardGameDTO1).isNotEqualTo(boardGameDTO2);
        boardGameDTO1.setId(null);
        assertThat(boardGameDTO1).isNotEqualTo(boardGameDTO2);
    }
}

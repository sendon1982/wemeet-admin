package org.wemeet.portal.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.wemeet.portal.domain.GameTestSamples.*;

import org.junit.jupiter.api.Test;
import org.wemeet.portal.web.rest.TestUtil;

class GameTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Game.class);
        Game game1 = getGameSample1();
        Game game2 = new Game();
        assertThat(game1).isNotEqualTo(game2);

        game2.setId(game1.getId());
        assertThat(game1).isEqualTo(game2);

        game2 = getGameSample2();
        assertThat(game1).isNotEqualTo(game2);
    }
}

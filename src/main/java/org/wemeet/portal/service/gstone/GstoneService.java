package org.wemeet.portal.service.gstone;

import jakarta.annotation.PostConstruct;
import java.util.List;
import org.wemeet.portal.domain.BoardGameV2;
import org.wemeet.portal.model.gstone.GstoneResponse;

public interface GstoneService {
    List<BoardGameV2> searchGames(
        String englishName,
        String chineseName,
        Integer minPlayers,
        Integer maxPlayers,
        String category,
        String theme
    );

    @PostConstruct
    GstoneResponse getRankList();
}

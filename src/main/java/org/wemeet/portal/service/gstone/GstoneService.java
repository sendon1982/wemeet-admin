package org.wemeet.portal.service.gstone;

import java.util.List;
import org.wemeet.portal.domain.BoardGameV2;
import org.wemeet.portal.model.gstone.GstoneResponse;

public interface GstoneService {
    BoardGameV2 findGameById(int gameId, Boolean includeExpansion);

    List<BoardGameV2> getGameListByIds(List<Integer> gameIds);

    List<BoardGameV2> searchGames(String name, Integer minPlayers, Integer maxPlayers, String category, String theme);

    GstoneResponse getRankList();
}

package org.wemeet.portal.util;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.wemeet.portal.domain.RelationGameIndex;

public class GameUtil {

    public static Set<Integer> filterExpansionGameIds(List<RelationGameIndex> relationGameList) {
        if (relationGameList == null) {
            return Set.of();
        }

        return relationGameList.stream().filter(GameUtil::isExpansionGame).map(RelationGameIndex::getId).collect(Collectors.toSet());
    }

    public static List<RelationGameIndex> filterExpansionGames(List<RelationGameIndex> relationGameList) {
        return relationGameList.stream().filter(GameUtil::isExpansionGame).collect(Collectors.toList());
    }

    private static boolean isExpansionGame(RelationGameIndex relationGameIndex) {
        if (relationGameIndex == null || relationGameIndex.is_expansion == 0) {
            return false;
        }

        return relationGameIndex.is_expansion == 1 && relationGameIndex.expansion_type == 751;
    }
}

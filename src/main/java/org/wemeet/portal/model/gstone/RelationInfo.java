package org.wemeet.portal.model.gstone;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.wemeet.portal.domain.RelationGameIndex;

@Getter
@Setter
public class RelationInfo {

    @JsonProperty("relation_game_ids")
    private List<Integer> relationGameIds;

    @JsonProperty("relation_index_ls")
    public List<RelationGameIndex> relationIndices;
}

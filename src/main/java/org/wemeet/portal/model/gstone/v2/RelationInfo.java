package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "relation_game_ids", "num", "relation_index_ls" })
public class RelationInfo {

    @JsonProperty("relation_game_ids")
    private List<Integer> relationGameIds;

    @JsonProperty("num")
    private Integer num;

    @JsonProperty("relation_index_ls")
    private List<RelationIndexL> relationIndexLs;

    @JsonProperty("relation_game_ids")
    public List<Integer> getRelationGameIds() {
        return relationGameIds;
    }

    @JsonProperty("relation_game_ids")
    public void setRelationGameIds(List<Integer> relationGameIds) {
        this.relationGameIds = relationGameIds;
    }

    @JsonProperty("num")
    public Integer getNum() {
        return num;
    }

    @JsonProperty("num")
    public void setNum(Integer num) {
        this.num = num;
    }

    @JsonProperty("relation_index_ls")
    public List<RelationIndexL> getRelationIndexLs() {
        return relationIndexLs;
    }

    @JsonProperty("relation_index_ls")
    public void setRelationIndexLs(List<RelationIndexL> relationIndexLs) {
        this.relationIndexLs = relationIndexLs;
    }
}

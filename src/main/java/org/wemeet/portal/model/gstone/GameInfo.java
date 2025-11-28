package org.wemeet.portal.model.gstone;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GameInfo {

    public String p_description;
    public String primary_language;
    public String p_name;
    public int id;
    public String description;
    public int bgg_id;
    public String name;

    @JsonProperty("category")
    public List<Category> categories;

    @JsonProperty("is_expansion")
    private boolean isExpansion;

    @JsonProperty("expansion_type")
    private int expansionType;

    @JsonProperty("relation_info")
    private RelationInfo relationInfo;
}

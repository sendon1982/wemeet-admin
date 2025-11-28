package org.wemeet.portal.model.gstone;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Game {

    @JsonProperty("is_mm")
    private int isMm;

    @JsonProperty("total_time")
    private int totalTime;

    @JsonProperty("bgg_id")
    private int bggId;

    @JsonProperty("name")
    private String chineseName;

    @JsonProperty("p_name")
    private String englishName;

    @JsonProperty("description")
    private String chineseDescription;

    @JsonProperty("p_description")
    private String englishDescription;

    @JsonProperty("sub_category")
    private List<Object> subCategory; // Or a specific SubCategory class

    @JsonProperty("pro_category")
    private List<Object> proCategory; // Or a specific ProCategory class

    @JsonProperty("is_expansion")
    private int isExpansion;

    @JsonProperty("mod_type")
    private int modType;

    @JsonProperty("is_pg")
    private int isPg;

    @JsonProperty("box_url")
    private String boxUrl;

    @JsonProperty("primary_language")
    private String primaryLanguage;

    @JsonProperty("id")
    private int id;

    @JsonProperty("category")
    private List<Category> category;

    @JsonProperty("cover_url_s")
    private String coverUrlS;

    @JsonProperty("if_login_info")
    private Map<String, Object> ifLoginInfo; // Or a specific IfLoginInfo class

    @JsonProperty("average_time_per_player")
    private int averageTimePerPlayer;

    @JsonProperty("theme")
    private List<Theme> theme;

    @JsonProperty("sales_mode_id")
    private int salesModeId;

    @JsonProperty("role_sex_info")
    private RoleSexInfo roleSexInfo;

    @JsonProperty("sales_mode")
    private SalesMode salesMode;

    @JsonProperty("status")
    private Status status;

    @JsonProperty("designer")
    private String designer;

    @JsonProperty("expansion_type")
    private int expansionType;

    @JsonProperty("width_height")
    private double widthHeight;

    @JsonProperty("is_glight")
    private int isGlight;

    @JsonProperty("order_info")
    private OrderInfo orderInfo;

    @JsonProperty("cover_url")
    private String coverUrl;

    @JsonProperty("bayesaverage")
    private double bayesaverage;

    @JsonProperty("player_num")
    private List<Integer> playerNum;

    @JsonProperty("difficulty")
    private int difficulty;

    @JsonProperty("mm_rating_info")
    private Map<String, Object> mmRatingInfo; // Or a specific MmRatingInfo class

    @JsonProperty("relation_info")
    private RelationInfo relationInfo;

    @JsonProperty("game_hotness_value")
    private double gameHotnessValue;

    @JsonProperty("publish_year")
    private int publishYear;

    @JsonProperty("mode")
    private Mode mode;

    @JsonProperty("box_url_s")
    private String boxUrlS;

    @JsonProperty("gstone_rating")
    private double gstoneRating;

    @JsonProperty("price_info")
    private Map<String, Object> priceInfo;
}

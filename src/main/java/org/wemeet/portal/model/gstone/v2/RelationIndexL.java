package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.wemeet.portal.domain.FirstPicture;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    {
        "is_mm",
        "sub_category",
        "is_expansion",
        "mod_type",
        "is_pg",
        "box_url",
        "primary_language",
        "id",
        "cover_url_s",
        "publish_year",
        "sales_mode",
        "status",
        "expansion_type",
        "width_height",
        "is_glight",
        "cover_url",
        "first_picture",
        "status_content",
        "name",
        "game_hotness_value",
        "sales_mode_id",
        "box_url_s",
        "publish_month",
    }
)
public class RelationIndexL {

    @JsonProperty("is_mm")
    private Integer isMm;

    @JsonProperty("sub_category")
    private String subCategory;

    @JsonProperty("is_expansion")
    private Integer isExpansion;

    @JsonProperty("mod_type")
    private Integer modType;

    @JsonProperty("is_pg")
    private Integer isPg;

    @JsonProperty("box_url")
    private String boxUrl;

    @JsonProperty("primary_language")
    private String primaryLanguage;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("cover_url_s")
    private String coverUrlS;

    @JsonProperty("publish_year")
    private Integer publishYear;

    @JsonProperty("sales_mode")
    private SalesMode__2 salesMode;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("expansion_type")
    private Integer expansionType;

    @JsonProperty("width_height")
    private Double widthHeight;

    @JsonProperty("is_glight")
    private Integer isGlight;

    @JsonProperty("cover_url")
    private String coverUrl;

    @JsonProperty("first_picture")
    private FirstPicture firstPicture;

    @JsonProperty("status_content")
    private StatusContent__1 statusContent;

    @JsonProperty("name")
    private String name;

    @JsonProperty("game_hotness_value")
    private Double gameHotnessValue;

    @JsonProperty("sales_mode_id")
    private Integer salesModeId;

    @JsonProperty("box_url_s")
    private String boxUrlS;

    @JsonProperty("publish_month")
    private Integer publishMonth;

    @JsonProperty("is_mm")
    public Integer getIsMm() {
        return isMm;
    }

    @JsonProperty("is_mm")
    public void setIsMm(Integer isMm) {
        this.isMm = isMm;
    }

    @JsonProperty("sub_category")
    public String getSubCategory() {
        return subCategory;
    }

    @JsonProperty("sub_category")
    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    @JsonProperty("is_expansion")
    public Integer getIsExpansion() {
        return isExpansion;
    }

    @JsonProperty("is_expansion")
    public void setIsExpansion(Integer isExpansion) {
        this.isExpansion = isExpansion;
    }

    @JsonProperty("mod_type")
    public Integer getModType() {
        return modType;
    }

    @JsonProperty("mod_type")
    public void setModType(Integer modType) {
        this.modType = modType;
    }

    @JsonProperty("is_pg")
    public Integer getIsPg() {
        return isPg;
    }

    @JsonProperty("is_pg")
    public void setIsPg(Integer isPg) {
        this.isPg = isPg;
    }

    @JsonProperty("box_url")
    public String getBoxUrl() {
        return boxUrl;
    }

    @JsonProperty("box_url")
    public void setBoxUrl(String boxUrl) {
        this.boxUrl = boxUrl;
    }

    @JsonProperty("primary_language")
    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    @JsonProperty("primary_language")
    public void setPrimaryLanguage(String primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("cover_url_s")
    public String getCoverUrlS() {
        return coverUrlS;
    }

    @JsonProperty("cover_url_s")
    public void setCoverUrlS(String coverUrlS) {
        this.coverUrlS = coverUrlS;
    }

    @JsonProperty("publish_year")
    public Integer getPublishYear() {
        return publishYear;
    }

    @JsonProperty("publish_year")
    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    @JsonProperty("sales_mode")
    public SalesMode__2 getSalesMode() {
        return salesMode;
    }

    @JsonProperty("sales_mode")
    public void setSalesMode(SalesMode__2 salesMode) {
        this.salesMode = salesMode;
    }

    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonProperty("expansion_type")
    public Integer getExpansionType() {
        return expansionType;
    }

    @JsonProperty("expansion_type")
    public void setExpansionType(Integer expansionType) {
        this.expansionType = expansionType;
    }

    @JsonProperty("width_height")
    public Double getWidthHeight() {
        return widthHeight;
    }

    @JsonProperty("width_height")
    public void setWidthHeight(Double widthHeight) {
        this.widthHeight = widthHeight;
    }

    @JsonProperty("is_glight")
    public Integer getIsGlight() {
        return isGlight;
    }

    @JsonProperty("is_glight")
    public void setIsGlight(Integer isGlight) {
        this.isGlight = isGlight;
    }

    @JsonProperty("cover_url")
    public String getCoverUrl() {
        return coverUrl;
    }

    @JsonProperty("cover_url")
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    @JsonProperty("first_picture")
    public FirstPicture getFirstPicture() {
        return firstPicture;
    }

    @JsonProperty("first_picture")
    public void setFirstPicture(FirstPicture firstPicture) {
        this.firstPicture = firstPicture;
    }

    @JsonProperty("status_content")
    public StatusContent__1 getStatusContent() {
        return statusContent;
    }

    @JsonProperty("status_content")
    public void setStatusContent(StatusContent__1 statusContent) {
        this.statusContent = statusContent;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("game_hotness_value")
    public Double getGameHotnessValue() {
        return gameHotnessValue;
    }

    @JsonProperty("game_hotness_value")
    public void setGameHotnessValue(Double gameHotnessValue) {
        this.gameHotnessValue = gameHotnessValue;
    }

    @JsonProperty("sales_mode_id")
    public Integer getSalesModeId() {
        return salesModeId;
    }

    @JsonProperty("sales_mode_id")
    public void setSalesModeId(Integer salesModeId) {
        this.salesModeId = salesModeId;
    }

    @JsonProperty("box_url_s")
    public String getBoxUrlS() {
        return boxUrlS;
    }

    @JsonProperty("box_url_s")
    public void setBoxUrlS(String boxUrlS) {
        this.boxUrlS = boxUrlS;
    }

    @JsonProperty("publish_month")
    public Integer getPublishMonth() {
        return publishMonth;
    }

    @JsonProperty("publish_month")
    public void setPublishMonth(Integer publishMonth) {
        this.publishMonth = publishMonth;
    }
}

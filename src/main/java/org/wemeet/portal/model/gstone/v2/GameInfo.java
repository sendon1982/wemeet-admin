package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    {
        "is_mm",
        "pro_category",
        "sub_category",
        "published_language",
        "is_expansion",
        "mod_type",
        "is_pg",
        "box_url",
        "gamelist_info",
        "all_rating_num",
        "document_info",
        "p_description",
        "primary_language",
        "ranking_ls",
        "language_requirement",
        "video_info_v2",
        "p_name",
        "id",
        "count_info",
        "description",
        "category",
        "gamefound",
        "need_dm",
        "cover_url_s",
        "if_login_info",
        "table_requirement",
        "average_time_per_player",
        "picture_info",
        "website",
        "theme",
        "sales_mode_id",
        "publisher_ls",
        "bgg_id",
        "role_sex_info",
        "designer_ls",
        "sales_mode",
        "total_time",
        "group_info",
        "designer",
        "expansion_type",
        "box_url_s",
        "width_height",
        "is_glight",
        "kickstarter",
        "other_info",
        "role_info",
        "cover_url",
        "setup_time",
        "difficulty",
        "minimum_age",
        "team_info",
        "player_num",
        "homepage_info",
        "publisher",
        "version_info",
        "name",
        "portability",
        "mm_rating_info",
        "medal_ls",
        "moudian",
        "usersrated",
        "relation_info",
        "game_hotness_value",
        "zeze",
        "video_info",
        "publish_year",
        "status",
        "mode",
        "mechanic",
        "gstone_rating",
        "similar_info",
        "publish_month",
    }
)
public class GameInfo {

    @JsonProperty("is_mm")
    private Integer isMm;

    @JsonProperty("pro_category")
    private List<Object> proCategory;

    @JsonProperty("sub_category")
    private List<Object> subCategory;

    @JsonProperty("published_language")
    private List<PublishedLanguage> publishedLanguage;

    @JsonProperty("is_expansion")
    private Integer isExpansion;

    @JsonProperty("mod_type")
    private Integer modType;

    @JsonProperty("is_pg")
    private Integer isPg;

    @JsonProperty("box_url")
    private String boxUrl;

    @JsonProperty("gamelist_info")
    private GamelistInfo gamelistInfo;

    @JsonProperty("all_rating_num")
    private Integer allRatingNum;

    @JsonProperty("document_info")
    private DocumentInfo documentInfo;

    @JsonProperty("p_description")
    private String pDescription;

    @JsonProperty("primary_language")
    private String primaryLanguage;

    @JsonProperty("ranking_ls")
    private List<RankingL> rankingLs;

    @JsonProperty("language_requirement")
    private LanguageRequirement languageRequirement;

    @JsonProperty("video_info_v2")
    private VideoInfoV2 videoInfoV2;

    @JsonProperty("p_name")
    private String pName;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("count_info")
    private CountInfo countInfo;

    @JsonProperty("description")
    private String description;

    @JsonProperty("category")
    private List<Category> category;

    @JsonProperty("gamefound")
    private String gamefound;

    @JsonProperty("need_dm")
    private Integer needDm;

    @JsonProperty("cover_url_s")
    private String coverUrlS;

    @JsonProperty("if_login_info")
    private IfLoginInfo__1 ifLoginInfo;

    @JsonProperty("table_requirement")
    private TableRequirement tableRequirement;

    @JsonProperty("average_time_per_player")
    private Integer averageTimePerPlayer;

    @JsonProperty("picture_info")
    private PictureInfo pictureInfo;

    @JsonProperty("website")
    private String website;

    @JsonProperty("theme")
    private List<Theme> theme;

    @JsonProperty("sales_mode_id")
    private Integer salesModeId;

    @JsonProperty("publisher_ls")
    private List<PublisherL> publisherLs;

    @JsonProperty("bgg_id")
    private Integer bggId;

    @JsonProperty("role_sex_info")
    private RoleSexInfo roleSexInfo;

    @JsonProperty("designer_ls")
    private List<DesignerL> designerLs;

    @JsonProperty("sales_mode")
    private SalesMode__1 salesMode;

    @JsonProperty("total_time")
    private Integer totalTime;

    @JsonProperty("group_info")
    private GroupInfo groupInfo;

    @JsonProperty("designer")
    private String designer;

    @JsonProperty("expansion_type")
    private Integer expansionType;

    @JsonProperty("box_url_s")
    private String boxUrlS;

    @JsonProperty("width_height")
    private Double widthHeight;

    @JsonProperty("is_glight")
    private Integer isGlight;

    @JsonProperty("kickstarter")
    private String kickstarter;

    @JsonProperty("other_info")
    private OtherInfo otherInfo;

    @JsonProperty("role_info")
    private RoleInfo roleInfo;

    @JsonProperty("cover_url")
    private String coverUrl;

    @JsonProperty("setup_time")
    private SetupTime setupTime;

    @JsonProperty("difficulty")
    private Integer difficulty;

    @JsonProperty("minimum_age")
    private Integer minimumAge;

    @JsonProperty("team_info")
    private TeamInfo teamInfo;

    @JsonProperty("player_num")
    private List<Integer> playerNum;

    @JsonProperty("homepage_info")
    private HomepageInfo homepageInfo;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("version_info")
    private VersionInfo versionInfo;

    @JsonProperty("name")
    private String name;

    @JsonProperty("portability")
    private Portability portability;

    @JsonProperty("mm_rating_info")
    private MmRatingInfo mmRatingInfo;

    @JsonProperty("medal_ls")
    private List<Object> medalLs;

    @JsonProperty("moudian")
    private String moudian;

    @JsonProperty("usersrated")
    private Integer usersrated;

    @JsonProperty("relation_info")
    private RelationInfo relationInfo;

    @JsonProperty("game_hotness_value")
    private Double gameHotnessValue;

    @JsonProperty("zeze")
    private String zeze;

    @JsonProperty("video_info")
    private VideoInfo videoInfo;

    @JsonProperty("publish_year")
    private Integer publishYear;

    @JsonProperty("status")
    private Status status;

    @JsonProperty("mode")
    private Mode mode;

    @JsonProperty("mechanic")
    private List<Mechanic> mechanic;

    @JsonProperty("gstone_rating")
    private Double gstoneRating;

    @JsonProperty("similar_info")
    private SimilarInfo similarInfo;

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

    @JsonProperty("pro_category")
    public List<Object> getProCategory() {
        return proCategory;
    }

    @JsonProperty("pro_category")
    public void setProCategory(List<Object> proCategory) {
        this.proCategory = proCategory;
    }

    @JsonProperty("sub_category")
    public List<Object> getSubCategory() {
        return subCategory;
    }

    @JsonProperty("sub_category")
    public void setSubCategory(List<Object> subCategory) {
        this.subCategory = subCategory;
    }

    @JsonProperty("published_language")
    public List<PublishedLanguage> getPublishedLanguage() {
        return publishedLanguage;
    }

    @JsonProperty("published_language")
    public void setPublishedLanguage(List<PublishedLanguage> publishedLanguage) {
        this.publishedLanguage = publishedLanguage;
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

    @JsonProperty("gamelist_info")
    public GamelistInfo getGamelistInfo() {
        return gamelistInfo;
    }

    @JsonProperty("gamelist_info")
    public void setGamelistInfo(GamelistInfo gamelistInfo) {
        this.gamelistInfo = gamelistInfo;
    }

    @JsonProperty("all_rating_num")
    public Integer getAllRatingNum() {
        return allRatingNum;
    }

    @JsonProperty("all_rating_num")
    public void setAllRatingNum(Integer allRatingNum) {
        this.allRatingNum = allRatingNum;
    }

    @JsonProperty("document_info")
    public DocumentInfo getDocumentInfo() {
        return documentInfo;
    }

    @JsonProperty("document_info")
    public void setDocumentInfo(DocumentInfo documentInfo) {
        this.documentInfo = documentInfo;
    }

    @JsonProperty("p_description")
    public String getpDescription() {
        return pDescription;
    }

    @JsonProperty("p_description")
    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    @JsonProperty("primary_language")
    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    @JsonProperty("primary_language")
    public void setPrimaryLanguage(String primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }

    @JsonProperty("ranking_ls")
    public List<RankingL> getRankingLs() {
        return rankingLs;
    }

    @JsonProperty("ranking_ls")
    public void setRankingLs(List<RankingL> rankingLs) {
        this.rankingLs = rankingLs;
    }

    @JsonProperty("language_requirement")
    public LanguageRequirement getLanguageRequirement() {
        return languageRequirement;
    }

    @JsonProperty("language_requirement")
    public void setLanguageRequirement(LanguageRequirement languageRequirement) {
        this.languageRequirement = languageRequirement;
    }

    @JsonProperty("video_info_v2")
    public VideoInfoV2 getVideoInfoV2() {
        return videoInfoV2;
    }

    @JsonProperty("video_info_v2")
    public void setVideoInfoV2(VideoInfoV2 videoInfoV2) {
        this.videoInfoV2 = videoInfoV2;
    }

    @JsonProperty("p_name")
    public String getpName() {
        return pName;
    }

    @JsonProperty("p_name")
    public void setpName(String pName) {
        this.pName = pName;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("count_info")
    public CountInfo getCountInfo() {
        return countInfo;
    }

    @JsonProperty("count_info")
    public void setCountInfo(CountInfo countInfo) {
        this.countInfo = countInfo;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("category")
    public List<Category> getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(List<Category> category) {
        this.category = category;
    }

    @JsonProperty("gamefound")
    public String getGamefound() {
        return gamefound;
    }

    @JsonProperty("gamefound")
    public void setGamefound(String gamefound) {
        this.gamefound = gamefound;
    }

    @JsonProperty("need_dm")
    public Integer getNeedDm() {
        return needDm;
    }

    @JsonProperty("need_dm")
    public void setNeedDm(Integer needDm) {
        this.needDm = needDm;
    }

    @JsonProperty("cover_url_s")
    public String getCoverUrlS() {
        return coverUrlS;
    }

    @JsonProperty("cover_url_s")
    public void setCoverUrlS(String coverUrlS) {
        this.coverUrlS = coverUrlS;
    }

    @JsonProperty("if_login_info")
    public IfLoginInfo__1 getIfLoginInfo() {
        return ifLoginInfo;
    }

    @JsonProperty("if_login_info")
    public void setIfLoginInfo(IfLoginInfo__1 ifLoginInfo) {
        this.ifLoginInfo = ifLoginInfo;
    }

    @JsonProperty("table_requirement")
    public TableRequirement getTableRequirement() {
        return tableRequirement;
    }

    @JsonProperty("table_requirement")
    public void setTableRequirement(TableRequirement tableRequirement) {
        this.tableRequirement = tableRequirement;
    }

    @JsonProperty("average_time_per_player")
    public Integer getAverageTimePerPlayer() {
        return averageTimePerPlayer;
    }

    @JsonProperty("average_time_per_player")
    public void setAverageTimePerPlayer(Integer averageTimePerPlayer) {
        this.averageTimePerPlayer = averageTimePerPlayer;
    }

    @JsonProperty("picture_info")
    public PictureInfo getPictureInfo() {
        return pictureInfo;
    }

    @JsonProperty("picture_info")
    public void setPictureInfo(PictureInfo pictureInfo) {
        this.pictureInfo = pictureInfo;
    }

    @JsonProperty("website")
    public String getWebsite() {
        return website;
    }

    @JsonProperty("website")
    public void setWebsite(String website) {
        this.website = website;
    }

    @JsonProperty("theme")
    public List<Theme> getTheme() {
        return theme;
    }

    @JsonProperty("theme")
    public void setTheme(List<Theme> theme) {
        this.theme = theme;
    }

    @JsonProperty("sales_mode_id")
    public Integer getSalesModeId() {
        return salesModeId;
    }

    @JsonProperty("sales_mode_id")
    public void setSalesModeId(Integer salesModeId) {
        this.salesModeId = salesModeId;
    }

    @JsonProperty("publisher_ls")
    public List<PublisherL> getPublisherLs() {
        return publisherLs;
    }

    @JsonProperty("publisher_ls")
    public void setPublisherLs(List<PublisherL> publisherLs) {
        this.publisherLs = publisherLs;
    }

    @JsonProperty("bgg_id")
    public Integer getBggId() {
        return bggId;
    }

    @JsonProperty("bgg_id")
    public void setBggId(Integer bggId) {
        this.bggId = bggId;
    }

    @JsonProperty("role_sex_info")
    public RoleSexInfo getRoleSexInfo() {
        return roleSexInfo;
    }

    @JsonProperty("role_sex_info")
    public void setRoleSexInfo(RoleSexInfo roleSexInfo) {
        this.roleSexInfo = roleSexInfo;
    }

    @JsonProperty("designer_ls")
    public List<DesignerL> getDesignerLs() {
        return designerLs;
    }

    @JsonProperty("designer_ls")
    public void setDesignerLs(List<DesignerL> designerLs) {
        this.designerLs = designerLs;
    }

    @JsonProperty("sales_mode")
    public SalesMode__1 getSalesMode() {
        return salesMode;
    }

    @JsonProperty("sales_mode")
    public void setSalesMode(SalesMode__1 salesMode) {
        this.salesMode = salesMode;
    }

    @JsonProperty("total_time")
    public Integer getTotalTime() {
        return totalTime;
    }

    @JsonProperty("total_time")
    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    @JsonProperty("group_info")
    public GroupInfo getGroupInfo() {
        return groupInfo;
    }

    @JsonProperty("group_info")
    public void setGroupInfo(GroupInfo groupInfo) {
        this.groupInfo = groupInfo;
    }

    @JsonProperty("designer")
    public String getDesigner() {
        return designer;
    }

    @JsonProperty("designer")
    public void setDesigner(String designer) {
        this.designer = designer;
    }

    @JsonProperty("expansion_type")
    public Integer getExpansionType() {
        return expansionType;
    }

    @JsonProperty("expansion_type")
    public void setExpansionType(Integer expansionType) {
        this.expansionType = expansionType;
    }

    @JsonProperty("box_url_s")
    public String getBoxUrlS() {
        return boxUrlS;
    }

    @JsonProperty("box_url_s")
    public void setBoxUrlS(String boxUrlS) {
        this.boxUrlS = boxUrlS;
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

    @JsonProperty("kickstarter")
    public String getKickstarter() {
        return kickstarter;
    }

    @JsonProperty("kickstarter")
    public void setKickstarter(String kickstarter) {
        this.kickstarter = kickstarter;
    }

    @JsonProperty("other_info")
    public OtherInfo getOtherInfo() {
        return otherInfo;
    }

    @JsonProperty("other_info")
    public void setOtherInfo(OtherInfo otherInfo) {
        this.otherInfo = otherInfo;
    }

    @JsonProperty("role_info")
    public RoleInfo getRoleInfo() {
        return roleInfo;
    }

    @JsonProperty("role_info")
    public void setRoleInfo(RoleInfo roleInfo) {
        this.roleInfo = roleInfo;
    }

    @JsonProperty("cover_url")
    public String getCoverUrl() {
        return coverUrl;
    }

    @JsonProperty("cover_url")
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    @JsonProperty("setup_time")
    public SetupTime getSetupTime() {
        return setupTime;
    }

    @JsonProperty("setup_time")
    public void setSetupTime(SetupTime setupTime) {
        this.setupTime = setupTime;
    }

    @JsonProperty("difficulty")
    public Integer getDifficulty() {
        return difficulty;
    }

    @JsonProperty("difficulty")
    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    @JsonProperty("minimum_age")
    public Integer getMinimumAge() {
        return minimumAge;
    }

    @JsonProperty("minimum_age")
    public void setMinimumAge(Integer minimumAge) {
        this.minimumAge = minimumAge;
    }

    @JsonProperty("team_info")
    public TeamInfo getTeamInfo() {
        return teamInfo;
    }

    @JsonProperty("team_info")
    public void setTeamInfo(TeamInfo teamInfo) {
        this.teamInfo = teamInfo;
    }

    @JsonProperty("player_num")
    public List<Integer> getPlayerNum() {
        return playerNum;
    }

    @JsonProperty("player_num")
    public void setPlayerNum(List<Integer> playerNum) {
        this.playerNum = playerNum;
    }

    @JsonProperty("homepage_info")
    public HomepageInfo getHomepageInfo() {
        return homepageInfo;
    }

    @JsonProperty("homepage_info")
    public void setHomepageInfo(HomepageInfo homepageInfo) {
        this.homepageInfo = homepageInfo;
    }

    @JsonProperty("publisher")
    public String getPublisher() {
        return publisher;
    }

    @JsonProperty("publisher")
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @JsonProperty("version_info")
    public VersionInfo getVersionInfo() {
        return versionInfo;
    }

    @JsonProperty("version_info")
    public void setVersionInfo(VersionInfo versionInfo) {
        this.versionInfo = versionInfo;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("portability")
    public Portability getPortability() {
        return portability;
    }

    @JsonProperty("portability")
    public void setPortability(Portability portability) {
        this.portability = portability;
    }

    @JsonProperty("mm_rating_info")
    public MmRatingInfo getMmRatingInfo() {
        return mmRatingInfo;
    }

    @JsonProperty("mm_rating_info")
    public void setMmRatingInfo(MmRatingInfo mmRatingInfo) {
        this.mmRatingInfo = mmRatingInfo;
    }

    @JsonProperty("medal_ls")
    public List<Object> getMedalLs() {
        return medalLs;
    }

    @JsonProperty("medal_ls")
    public void setMedalLs(List<Object> medalLs) {
        this.medalLs = medalLs;
    }

    @JsonProperty("moudian")
    public String getMoudian() {
        return moudian;
    }

    @JsonProperty("moudian")
    public void setMoudian(String moudian) {
        this.moudian = moudian;
    }

    @JsonProperty("usersrated")
    public Integer getUsersrated() {
        return usersrated;
    }

    @JsonProperty("usersrated")
    public void setUsersrated(Integer usersrated) {
        this.usersrated = usersrated;
    }

    @JsonProperty("relation_info")
    public RelationInfo getRelationInfo() {
        return relationInfo;
    }

    @JsonProperty("relation_info")
    public void setRelationInfo(RelationInfo relationInfo) {
        this.relationInfo = relationInfo;
    }

    @JsonProperty("game_hotness_value")
    public Double getGameHotnessValue() {
        return gameHotnessValue;
    }

    @JsonProperty("game_hotness_value")
    public void setGameHotnessValue(Double gameHotnessValue) {
        this.gameHotnessValue = gameHotnessValue;
    }

    @JsonProperty("zeze")
    public String getZeze() {
        return zeze;
    }

    @JsonProperty("zeze")
    public void setZeze(String zeze) {
        this.zeze = zeze;
    }

    @JsonProperty("video_info")
    public VideoInfo getVideoInfo() {
        return videoInfo;
    }

    @JsonProperty("video_info")
    public void setVideoInfo(VideoInfo videoInfo) {
        this.videoInfo = videoInfo;
    }

    @JsonProperty("publish_year")
    public Integer getPublishYear() {
        return publishYear;
    }

    @JsonProperty("publish_year")
    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Status status) {
        this.status = status;
    }

    @JsonProperty("mode")
    public Mode getMode() {
        return mode;
    }

    @JsonProperty("mode")
    public void setMode(Mode mode) {
        this.mode = mode;
    }

    @JsonProperty("mechanic")
    public List<Mechanic> getMechanic() {
        return mechanic;
    }

    @JsonProperty("mechanic")
    public void setMechanic(List<Mechanic> mechanic) {
        this.mechanic = mechanic;
    }

    @JsonProperty("gstone_rating")
    public Double getGstoneRating() {
        return gstoneRating;
    }

    @JsonProperty("gstone_rating")
    public void setGstoneRating(Double gstoneRating) {
        this.gstoneRating = gstoneRating;
    }

    @JsonProperty("similar_info")
    public SimilarInfo getSimilarInfo() {
        return similarInfo;
    }

    @JsonProperty("similar_info")
    public void setSimilarInfo(SimilarInfo similarInfo) {
        this.similarInfo = similarInfo;
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

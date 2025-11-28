package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    { "list_describe", "if_login_info", "game_info", "reply_num", "create_man_id", "like_num", "create_man_info", "list_name", "id" }
)
public class Gamelist {

    @JsonProperty("list_describe")
    private String listDescribe;

    @JsonProperty("if_login_info")
    private IfLoginInfo ifLoginInfo;

    @JsonProperty("game_info")
    private GameInfo__1 gameInfo;

    @JsonProperty("reply_num")
    private Integer replyNum;

    @JsonProperty("create_man_id")
    private Integer createManId;

    @JsonProperty("like_num")
    private Integer likeNum;

    @JsonProperty("create_man_info")
    private CreateManInfo createManInfo;

    @JsonProperty("list_name")
    private String listName;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("list_describe")
    public String getListDescribe() {
        return listDescribe;
    }

    @JsonProperty("list_describe")
    public void setListDescribe(String listDescribe) {
        this.listDescribe = listDescribe;
    }

    @JsonProperty("if_login_info")
    public IfLoginInfo getIfLoginInfo() {
        return ifLoginInfo;
    }

    @JsonProperty("if_login_info")
    public void setIfLoginInfo(IfLoginInfo ifLoginInfo) {
        this.ifLoginInfo = ifLoginInfo;
    }

    @JsonProperty("game_info")
    public GameInfo__1 getGameInfo() {
        return gameInfo;
    }

    @JsonProperty("game_info")
    public void setGameInfo(GameInfo__1 gameInfo) {
        this.gameInfo = gameInfo;
    }

    @JsonProperty("reply_num")
    public Integer getReplyNum() {
        return replyNum;
    }

    @JsonProperty("reply_num")
    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    @JsonProperty("create_man_id")
    public Integer getCreateManId() {
        return createManId;
    }

    @JsonProperty("create_man_id")
    public void setCreateManId(Integer createManId) {
        this.createManId = createManId;
    }

    @JsonProperty("like_num")
    public Integer getLikeNum() {
        return likeNum;
    }

    @JsonProperty("like_num")
    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    @JsonProperty("create_man_info")
    public CreateManInfo getCreateManInfo() {
        return createManInfo;
    }

    @JsonProperty("create_man_info")
    public void setCreateManInfo(CreateManInfo createManInfo) {
        this.createManInfo = createManInfo;
    }

    @JsonProperty("list_name")
    public String getListName() {
        return listName;
    }

    @JsonProperty("list_name")
    public void setListName(String listName) {
        this.listName = listName;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }
}

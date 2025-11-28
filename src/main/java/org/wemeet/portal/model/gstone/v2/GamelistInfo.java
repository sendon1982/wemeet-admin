package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "num", "gamelist_list" })
public class GamelistInfo {

    @JsonProperty("num")
    private Integer num;

    @JsonProperty("gamelist_list")
    private List<Gamelist> gamelistList;

    @JsonProperty("num")
    public Integer getNum() {
        return num;
    }

    @JsonProperty("num")
    public void setNum(Integer num) {
        this.num = num;
    }

    @JsonProperty("gamelist_list")
    public List<Gamelist> getGamelistList() {
        return gamelistList;
    }

    @JsonProperty("gamelist_list")
    public void setGamelistList(List<Gamelist> gamelistList) {
        this.gamelistList = gamelistList;
    }
}

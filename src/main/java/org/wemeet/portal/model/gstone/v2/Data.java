package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "game_info" })
public class Data {

    @JsonProperty("game_info")
    private GameInfo gameInfo;

    @JsonProperty("game_info")
    public GameInfo getGameInfo() {
        return gameInfo;
    }

    @JsonProperty("game_info")
    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }
}

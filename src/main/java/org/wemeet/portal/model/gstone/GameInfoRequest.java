package org.wemeet.portal.model.gstone;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameInfoRequest {

    @JsonProperty("game_id")
    private int gameId;
}

package org.wemeet.portal.model.gstone;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {

    @JsonProperty("rank_info")
    private RankInfo rankInfo;

    @JsonProperty("game_list")
    private List<Game> gameList;
}

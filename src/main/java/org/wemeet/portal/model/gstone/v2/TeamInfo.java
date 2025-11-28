package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "team_list", "num", "team_type" })
public class TeamInfo {

    @JsonProperty("team_list")
    private List<Object> teamList;

    @JsonProperty("num")
    private Integer num;

    @JsonProperty("team_type")
    private TeamType teamType;

    @JsonProperty("team_list")
    public List<Object> getTeamList() {
        return teamList;
    }

    @JsonProperty("team_list")
    public void setTeamList(List<Object> teamList) {
        this.teamList = teamList;
    }

    @JsonProperty("num")
    public Integer getNum() {
        return num;
    }

    @JsonProperty("num")
    public void setNum(Integer num) {
        this.num = num;
    }

    @JsonProperty("team_type")
    public TeamType getTeamType() {
        return teamType;
    }

    @JsonProperty("team_type")
    public void setTeamType(TeamType teamType) {
        this.teamType = teamType;
    }
}

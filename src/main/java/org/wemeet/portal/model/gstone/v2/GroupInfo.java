package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "num", "group_list" })
public class GroupInfo {

    @JsonProperty("num")
    private Integer num;

    @JsonProperty("group_list")
    private List<Group> groupList;

    @JsonProperty("num")
    public Integer getNum() {
        return num;
    }

    @JsonProperty("num")
    public void setNum(Integer num) {
        this.num = num;
    }

    @JsonProperty("group_list")
    public List<Group> getGroupList() {
        return groupList;
    }

    @JsonProperty("group_list")
    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }
}

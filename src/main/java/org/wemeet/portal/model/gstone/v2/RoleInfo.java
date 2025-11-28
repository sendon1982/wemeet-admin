package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "role_list", "num", "role_type" })
public class RoleInfo {

    @JsonProperty("role_list")
    private List<Object> roleList;

    @JsonProperty("num")
    private Integer num;

    @JsonProperty("role_type")
    private RoleType roleType;

    @JsonProperty("role_list")
    public List<Object> getRoleList() {
        return roleList;
    }

    @JsonProperty("role_list")
    public void setRoleList(List<Object> roleList) {
        this.roleList = roleList;
    }

    @JsonProperty("num")
    public Integer getNum() {
        return num;
    }

    @JsonProperty("num")
    public void setNum(Integer num) {
        this.num = num;
    }

    @JsonProperty("role_type")
    public RoleType getRoleType() {
        return roleType;
    }

    @JsonProperty("role_type")
    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}

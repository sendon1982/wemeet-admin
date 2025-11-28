package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "version_list", "num" })
public class VersionInfo {

    @JsonProperty("version_list")
    private List<Object> versionList;

    @JsonProperty("num")
    private Integer num;

    @JsonProperty("version_list")
    public List<Object> getVersionList() {
        return versionList;
    }

    @JsonProperty("version_list")
    public void setVersionList(List<Object> versionList) {
        this.versionList = versionList;
    }

    @JsonProperty("num")
    public Integer getNum() {
        return num;
    }

    @JsonProperty("num")
    public void setNum(Integer num) {
        this.num = num;
    }
}

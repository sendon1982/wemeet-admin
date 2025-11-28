package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "she_num", "it_num", "he_num" })
public class RoleSexInfo {

    @JsonProperty("she_num")
    private Integer sheNum;

    @JsonProperty("it_num")
    private Integer itNum;

    @JsonProperty("he_num")
    private Integer heNum;

    @JsonProperty("she_num")
    public Integer getSheNum() {
        return sheNum;
    }

    @JsonProperty("she_num")
    public void setSheNum(Integer sheNum) {
        this.sheNum = sheNum;
    }

    @JsonProperty("it_num")
    public Integer getItNum() {
        return itNum;
    }

    @JsonProperty("it_num")
    public void setItNum(Integer itNum) {
        this.itNum = itNum;
    }

    @JsonProperty("he_num")
    public Integer getHeNum() {
        return heNum;
    }

    @JsonProperty("he_num")
    public void setHeNum(Integer heNum) {
        this.heNum = heNum;
    }
}

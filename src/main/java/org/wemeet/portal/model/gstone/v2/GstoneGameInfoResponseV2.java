package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "status", "cache_flag", "reason", "data" })
public class GstoneGameInfoResponseV2 {

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("cache_flag")
    private Integer cacheFlag;

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("data")
    private Data data;

    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonProperty("cache_flag")
    public Integer getCacheFlag() {
        return cacheFlag;
    }

    @JsonProperty("cache_flag")
    public void setCacheFlag(Integer cacheFlag) {
        this.cacheFlag = cacheFlag;
    }

    @JsonProperty("reason")
    public String getReason() {
        return reason;
    }

    @JsonProperty("reason")
    public void setReason(String reason) {
        this.reason = reason;
    }

    @JsonProperty("data")
    public Data getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Data data) {
        this.data = data;
    }
}

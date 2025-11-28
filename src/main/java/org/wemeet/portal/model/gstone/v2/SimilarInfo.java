package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "similar_ls", "num" })
public class SimilarInfo {

    @JsonProperty("similar_ls")
    private List<SimilarL> similarLs;

    @JsonProperty("num")
    private Integer num;

    @JsonProperty("similar_ls")
    public List<SimilarL> getSimilarLs() {
        return similarLs;
    }

    @JsonProperty("similar_ls")
    public void setSimilarLs(List<SimilarL> similarLs) {
        this.similarLs = similarLs;
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

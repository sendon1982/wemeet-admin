package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "picture_ls", "num" })
public class PictureInfo {

    @JsonProperty("picture_ls")
    private List<PictureL> pictureLs;

    @JsonProperty("num")
    private Integer num;

    @JsonProperty("picture_ls")
    public List<PictureL> getPictureLs() {
        return pictureLs;
    }

    @JsonProperty("picture_ls")
    public void setPictureLs(List<PictureL> pictureLs) {
        this.pictureLs = pictureLs;
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

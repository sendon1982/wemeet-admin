package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "picture_url", "picture_url_s", "width_height" })
public class FirstPicture {

    @JsonProperty("picture_url")
    private String pictureUrl;

    @JsonProperty("picture_url_s")
    private String pictureUrlS;

    @JsonProperty("width_height")
    private Double widthHeight;

    @JsonProperty("picture_url")
    public String getPictureUrl() {
        return pictureUrl;
    }

    @JsonProperty("picture_url")
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @JsonProperty("picture_url_s")
    public String getPictureUrlS() {
        return pictureUrlS;
    }

    @JsonProperty("picture_url_s")
    public void setPictureUrlS(String pictureUrlS) {
        this.pictureUrlS = pictureUrlS;
    }

    @JsonProperty("width_height")
    public Double getWidthHeight() {
        return widthHeight;
    }

    @JsonProperty("width_height")
    public void setWidthHeight(Double widthHeight) {
        this.widthHeight = widthHeight;
    }
}

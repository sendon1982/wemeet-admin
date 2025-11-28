package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "picture_url", "picture_url_s", "id" })
public class PictureL {

    @JsonProperty("picture_url")
    private String pictureUrl;

    @JsonProperty("picture_url_s")
    private String pictureUrlS;

    @JsonProperty("id")
    private Integer id;

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

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }
}

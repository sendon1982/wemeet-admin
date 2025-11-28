package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "num", "gallery_ls" })
public class HomepageInfo {

    @JsonProperty("num")
    private Integer num;

    @JsonProperty("gallery_ls")
    private List<GalleryL> galleryLs;

    @JsonProperty("num")
    public Integer getNum() {
        return num;
    }

    @JsonProperty("num")
    public void setNum(Integer num) {
        this.num = num;
    }

    @JsonProperty("gallery_ls")
    public List<GalleryL> getGalleryLs() {
        return galleryLs;
    }

    @JsonProperty("gallery_ls")
    public void setGalleryLs(List<GalleryL> galleryLs) {
        this.galleryLs = galleryLs;
    }
}

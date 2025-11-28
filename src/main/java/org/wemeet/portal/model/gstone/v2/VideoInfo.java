package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "video_ls", "num" })
public class VideoInfo {

    @JsonProperty("video_ls")
    private List<VideoL__1> videoLs;

    @JsonProperty("num")
    private Integer num;

    @JsonProperty("video_ls")
    public List<VideoL__1> getVideoLs() {
        return videoLs;
    }

    @JsonProperty("video_ls")
    public void setVideoLs(List<VideoL__1> videoLs) {
        this.videoLs = videoLs;
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

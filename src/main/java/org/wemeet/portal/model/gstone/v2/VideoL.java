package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    {
        "key_frame",
        "title",
        "video_url_youtube",
        "video_url_youku",
        "article_info",
        "video_type",
        "video_url_aiqiyi",
        "video_url_other",
        "video_url_tencent",
        "key_frame_s",
        "id",
        "video_url_B",
    }
)
public class VideoL {

    @JsonProperty("key_frame")
    private String keyFrame;

    @JsonProperty("title")
    private String title;

    @JsonProperty("video_url_youtube")
    private String videoUrlYoutube;

    @JsonProperty("video_url_youku")
    private String videoUrlYouku;

    @JsonProperty("article_info")
    private ArticleInfo articleInfo;

    @JsonProperty("video_type")
    private List<VideoType> videoType;

    @JsonProperty("video_url_aiqiyi")
    private String videoUrlAiqiyi;

    @JsonProperty("video_url_other")
    private String videoUrlOther;

    @JsonProperty("video_url_tencent")
    private String videoUrlTencent;

    @JsonProperty("key_frame_s")
    private String keyFrameS;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("video_url_B")
    private String videoUrlB;

    @JsonProperty("key_frame")
    public String getKeyFrame() {
        return keyFrame;
    }

    @JsonProperty("key_frame")
    public void setKeyFrame(String keyFrame) {
        this.keyFrame = keyFrame;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("video_url_youtube")
    public String getVideoUrlYoutube() {
        return videoUrlYoutube;
    }

    @JsonProperty("video_url_youtube")
    public void setVideoUrlYoutube(String videoUrlYoutube) {
        this.videoUrlYoutube = videoUrlYoutube;
    }

    @JsonProperty("video_url_youku")
    public String getVideoUrlYouku() {
        return videoUrlYouku;
    }

    @JsonProperty("video_url_youku")
    public void setVideoUrlYouku(String videoUrlYouku) {
        this.videoUrlYouku = videoUrlYouku;
    }

    @JsonProperty("article_info")
    public ArticleInfo getArticleInfo() {
        return articleInfo;
    }

    @JsonProperty("article_info")
    public void setArticleInfo(ArticleInfo articleInfo) {
        this.articleInfo = articleInfo;
    }

    @JsonProperty("video_type")
    public List<VideoType> getVideoType() {
        return videoType;
    }

    @JsonProperty("video_type")
    public void setVideoType(List<VideoType> videoType) {
        this.videoType = videoType;
    }

    @JsonProperty("video_url_aiqiyi")
    public String getVideoUrlAiqiyi() {
        return videoUrlAiqiyi;
    }

    @JsonProperty("video_url_aiqiyi")
    public void setVideoUrlAiqiyi(String videoUrlAiqiyi) {
        this.videoUrlAiqiyi = videoUrlAiqiyi;
    }

    @JsonProperty("video_url_other")
    public String getVideoUrlOther() {
        return videoUrlOther;
    }

    @JsonProperty("video_url_other")
    public void setVideoUrlOther(String videoUrlOther) {
        this.videoUrlOther = videoUrlOther;
    }

    @JsonProperty("video_url_tencent")
    public String getVideoUrlTencent() {
        return videoUrlTencent;
    }

    @JsonProperty("video_url_tencent")
    public void setVideoUrlTencent(String videoUrlTencent) {
        this.videoUrlTencent = videoUrlTencent;
    }

    @JsonProperty("key_frame_s")
    public String getKeyFrameS() {
        return keyFrameS;
    }

    @JsonProperty("key_frame_s")
    public void setKeyFrameS(String keyFrameS) {
        this.keyFrameS = keyFrameS;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("video_url_B")
    public String getVideoUrlB() {
        return videoUrlB;
    }

    @JsonProperty("video_url_B")
    public void setVideoUrlB(String videoUrlB) {
        this.videoUrlB = videoUrlB;
    }
}

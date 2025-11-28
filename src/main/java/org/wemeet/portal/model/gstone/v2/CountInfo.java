package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "discuss_num", "comment_num", "article_num" })
public class CountInfo {

    @JsonProperty("discuss_num")
    private Integer discussNum;

    @JsonProperty("comment_num")
    private Integer commentNum;

    @JsonProperty("article_num")
    private Integer articleNum;

    @JsonProperty("discuss_num")
    public Integer getDiscussNum() {
        return discussNum;
    }

    @JsonProperty("discuss_num")
    public void setDiscussNum(Integer discussNum) {
        this.discussNum = discussNum;
    }

    @JsonProperty("comment_num")
    public Integer getCommentNum() {
        return commentNum;
    }

    @JsonProperty("comment_num")
    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    @JsonProperty("article_num")
    public Integer getArticleNum() {
        return articleNum;
    }

    @JsonProperty("article_num")
    public void setArticleNum(Integer articleNum) {
        this.articleNum = articleNum;
    }
}

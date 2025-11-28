package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    { "document_title", "document_image_url_s", "document_image_url", "create_time", "document_url", "document_image_width_height", "id" }
)
public class Doc {

    @JsonProperty("document_title")
    private String documentTitle;

    @JsonProperty("document_image_url_s")
    private String documentImageUrlS;

    @JsonProperty("document_image_url")
    private String documentImageUrl;

    @JsonProperty("create_time")
    private String createTime;

    @JsonProperty("document_url")
    private String documentUrl;

    @JsonProperty("document_image_width_height")
    private Double documentImageWidthHeight;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("document_title")
    public String getDocumentTitle() {
        return documentTitle;
    }

    @JsonProperty("document_title")
    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    @JsonProperty("document_image_url_s")
    public String getDocumentImageUrlS() {
        return documentImageUrlS;
    }

    @JsonProperty("document_image_url_s")
    public void setDocumentImageUrlS(String documentImageUrlS) {
        this.documentImageUrlS = documentImageUrlS;
    }

    @JsonProperty("document_image_url")
    public String getDocumentImageUrl() {
        return documentImageUrl;
    }

    @JsonProperty("document_image_url")
    public void setDocumentImageUrl(String documentImageUrl) {
        this.documentImageUrl = documentImageUrl;
    }

    @JsonProperty("create_time")
    public String getCreateTime() {
        return createTime;
    }

    @JsonProperty("create_time")
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @JsonProperty("document_url")
    public String getDocumentUrl() {
        return documentUrl;
    }

    @JsonProperty("document_url")
    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    @JsonProperty("document_image_width_height")
    public Double getDocumentImageWidthHeight() {
        return documentImageWidthHeight;
    }

    @JsonProperty("document_image_width_height")
    public void setDocumentImageWidthHeight(Double documentImageWidthHeight) {
        this.documentImageWidthHeight = documentImageWidthHeight;
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

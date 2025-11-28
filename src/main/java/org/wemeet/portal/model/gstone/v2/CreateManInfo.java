package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "photo", "detail_info", "sex", "is_master", "nickname", "id" })
public class CreateManInfo {

    @JsonProperty("photo")
    private String photo;

    @JsonProperty("detail_info")
    private DetailInfo detailInfo;

    @JsonProperty("sex")
    private Integer sex;

    @JsonProperty("is_master")
    private Integer isMaster;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("photo")
    public String getPhoto() {
        return photo;
    }

    @JsonProperty("photo")
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @JsonProperty("detail_info")
    public DetailInfo getDetailInfo() {
        return detailInfo;
    }

    @JsonProperty("detail_info")
    public void setDetailInfo(DetailInfo detailInfo) {
        this.detailInfo = detailInfo;
    }

    @JsonProperty("sex")
    public Integer getSex() {
        return sex;
    }

    @JsonProperty("sex")
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @JsonProperty("is_master")
    public Integer getIsMaster() {
        return isMaster;
    }

    @JsonProperty("is_master")
    public void setIsMaster(Integer isMaster) {
        this.isMaster = isMaster;
    }

    @JsonProperty("nickname")
    public String getNickname() {
        return nickname;
    }

    @JsonProperty("nickname")
    public void setNickname(String nickname) {
        this.nickname = nickname;
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

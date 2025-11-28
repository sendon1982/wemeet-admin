package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "vip_photo", "vip_type", "end_time", "is_vip" })
public class VipInfo {

    @JsonProperty("vip_photo")
    private String vipPhoto;

    @JsonProperty("vip_type")
    private Integer vipType;

    @JsonProperty("end_time")
    private String endTime;

    @JsonProperty("is_vip")
    private Integer isVip;

    @JsonProperty("vip_photo")
    public String getVipPhoto() {
        return vipPhoto;
    }

    @JsonProperty("vip_photo")
    public void setVipPhoto(String vipPhoto) {
        this.vipPhoto = vipPhoto;
    }

    @JsonProperty("vip_type")
    public Integer getVipType() {
        return vipType;
    }

    @JsonProperty("vip_type")
    public void setVipType(Integer vipType) {
        this.vipType = vipType;
    }

    @JsonProperty("end_time")
    public String getEndTime() {
        return endTime;
    }

    @JsonProperty("end_time")
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @JsonProperty("is_vip")
    public Integer getIsVip() {
        return isVip;
    }

    @JsonProperty("is_vip")
    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }
}

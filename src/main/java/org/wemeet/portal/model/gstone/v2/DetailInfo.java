package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "exp_level", "title", "mm_is_answer", "organizer_medal_ls", "vip_info", "badge" })
public class DetailInfo {

    @JsonProperty("exp_level")
    private Integer expLevel;

    @JsonProperty("title")
    private Title title;

    @JsonProperty("mm_is_answer")
    private Integer mmIsAnswer;

    @JsonProperty("organizer_medal_ls")
    private List<Object> organizerMedalLs;

    @JsonProperty("vip_info")
    private VipInfo vipInfo;

    @JsonProperty("badge")
    private String badge;

    @JsonProperty("exp_level")
    public Integer getExpLevel() {
        return expLevel;
    }

    @JsonProperty("exp_level")
    public void setExpLevel(Integer expLevel) {
        this.expLevel = expLevel;
    }

    @JsonProperty("title")
    public Title getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(Title title) {
        this.title = title;
    }

    @JsonProperty("mm_is_answer")
    public Integer getMmIsAnswer() {
        return mmIsAnswer;
    }

    @JsonProperty("mm_is_answer")
    public void setMmIsAnswer(Integer mmIsAnswer) {
        this.mmIsAnswer = mmIsAnswer;
    }

    @JsonProperty("organizer_medal_ls")
    public List<Object> getOrganizerMedalLs() {
        return organizerMedalLs;
    }

    @JsonProperty("organizer_medal_ls")
    public void setOrganizerMedalLs(List<Object> organizerMedalLs) {
        this.organizerMedalLs = organizerMedalLs;
    }

    @JsonProperty("vip_info")
    public VipInfo getVipInfo() {
        return vipInfo;
    }

    @JsonProperty("vip_info")
    public void setVipInfo(VipInfo vipInfo) {
        this.vipInfo = vipInfo;
    }

    @JsonProperty("badge")
    public String getBadge() {
        return badge;
    }

    @JsonProperty("badge")
    public void setBadge(String badge) {
        this.badge = badge;
    }
}

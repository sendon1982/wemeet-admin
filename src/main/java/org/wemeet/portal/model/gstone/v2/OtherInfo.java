package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "website", "crowdfunding_web_ls", "bgg_id", "sleeves_ls", "app_ls" })
public class OtherInfo {

    @JsonProperty("website")
    private String website;

    @JsonProperty("crowdfunding_web_ls")
    private List<Object> crowdfundingWebLs;

    @JsonProperty("bgg_id")
    private Integer bggId;

    @JsonProperty("sleeves_ls")
    private List<SleevesL> sleevesLs;

    @JsonProperty("app_ls")
    private List<AppL> appLs;

    @JsonProperty("website")
    public String getWebsite() {
        return website;
    }

    @JsonProperty("website")
    public void setWebsite(String website) {
        this.website = website;
    }

    @JsonProperty("crowdfunding_web_ls")
    public List<Object> getCrowdfundingWebLs() {
        return crowdfundingWebLs;
    }

    @JsonProperty("crowdfunding_web_ls")
    public void setCrowdfundingWebLs(List<Object> crowdfundingWebLs) {
        this.crowdfundingWebLs = crowdfundingWebLs;
    }

    @JsonProperty("bgg_id")
    public Integer getBggId() {
        return bggId;
    }

    @JsonProperty("bgg_id")
    public void setBggId(Integer bggId) {
        this.bggId = bggId;
    }

    @JsonProperty("sleeves_ls")
    public List<SleevesL> getSleevesLs() {
        return sleevesLs;
    }

    @JsonProperty("sleeves_ls")
    public void setSleevesLs(List<SleevesL> sleevesLs) {
        this.sleevesLs = sleevesLs;
    }

    @JsonProperty("app_ls")
    public List<AppL> getAppLs() {
        return appLs;
    }

    @JsonProperty("app_ls")
    public void setAppLs(List<AppL> appLs) {
        this.appLs = appLs;
    }
}

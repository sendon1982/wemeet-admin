package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "app_type", "name", "app_store", "app_download_url", "game_id", "id" })
public class AppL {

    @JsonProperty("app_type")
    private AppType appType;

    @JsonProperty("name")
    private String name;

    @JsonProperty("app_store")
    private Integer appStore;

    @JsonProperty("app_download_url")
    private String appDownloadUrl;

    @JsonProperty("game_id")
    private Integer gameId;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("app_type")
    public AppType getAppType() {
        return appType;
    }

    @JsonProperty("app_type")
    public void setAppType(AppType appType) {
        this.appType = appType;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("app_store")
    public Integer getAppStore() {
        return appStore;
    }

    @JsonProperty("app_store")
    public void setAppStore(Integer appStore) {
        this.appStore = appStore;
    }

    @JsonProperty("app_download_url")
    public String getAppDownloadUrl() {
        return appDownloadUrl;
    }

    @JsonProperty("app_download_url")
    public void setAppDownloadUrl(String appDownloadUrl) {
        this.appDownloadUrl = appDownloadUrl;
    }

    @JsonProperty("game_id")
    public Integer getGameId() {
        return gameId;
    }

    @JsonProperty("game_id")
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
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

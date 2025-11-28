package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "title_sch", "title_eng" })
public class Title {

    @JsonProperty("title_sch")
    private String titleSch;

    @JsonProperty("title_eng")
    private String titleEng;

    @JsonProperty("title_sch")
    public String getTitleSch() {
        return titleSch;
    }

    @JsonProperty("title_sch")
    public void setTitleSch(String titleSch) {
        this.titleSch = titleSch;
    }

    @JsonProperty("title_eng")
    public String getTitleEng() {
        return titleEng;
    }

    @JsonProperty("title_eng")
    public void setTitleEng(String titleEng) {
        this.titleEng = titleEng;
    }
}

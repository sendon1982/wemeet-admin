package org.wemeet.portal.model.gstone;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankInfo {

    @JsonProperty("user_info")
    private Map<String, Object> userInfo; // Or a specific UserInfo class if you have one

    @JsonProperty("share_title")
    private String shareTitle;

    @JsonProperty("played_num")
    private int playedNum;

    @JsonProperty("order_remark")
    private String orderRemark;
}

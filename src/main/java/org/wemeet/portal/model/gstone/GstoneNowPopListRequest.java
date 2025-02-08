package org.wemeet.portal.model.gstone;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GstoneNowPopListRequest {

    @JsonProperty("zone_id")
    private int zoneId;

    private int page;
}

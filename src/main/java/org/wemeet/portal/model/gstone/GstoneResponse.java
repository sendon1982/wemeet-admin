package org.wemeet.portal.model.gstone;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GstoneResponse {

    @JsonProperty("status")
    private int status;

    @JsonProperty("cache_flag")
    private int cacheFlag;

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("data")
    private Data data;
}

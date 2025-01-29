package org.wemeet.portal.model.gstone;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GstoneResponse {

    private int status;
    private int cacheFlag;
    private String reason;
    private Data data;
    // Getters and setters
}

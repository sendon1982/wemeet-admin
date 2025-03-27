package org.wemeet.portal.model.gstone;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class GameInfoResponse {

    public int status;
    public int cache_flag;
    public String reason;

    public GameResponseData data;
}

package org.wemeet.portal.service.gstone;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.wemeet.portal.model.gstone.Game;
import org.wemeet.portal.model.gstone.GstoneRequest;
import org.wemeet.portal.model.gstone.GstoneResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class GstoneServiceImpl implements GstoneService {

    private final RestTemplate restTemplate;

    public GstoneResponse getRankList(GstoneRequest request) {
        String url = "https://www.gstonegames.com/app/v2/rank_list_get/";

        ResponseEntity<GstoneResponse> responseEntity = restTemplate.postForEntity(url, request, GstoneResponse.class);
        GstoneResponse response = responseEntity.getBody();

        for (Game game : response.getData().getGameList()) {}
    }
}

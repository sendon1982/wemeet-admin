package org.wemeet.portal.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wemeet.portal.service.gstone.GstoneService;

@RestController
@RequestMapping("/api/util")
@RequiredArgsConstructor
public class UtilRestController {

    private final GstoneService gstoneService;

    @GetMapping("/games")
    public ResponseEntity<Void> postBoardGames() {
        System.out.println("================================================");

        //GstoneResponse rankList = gstoneService.getRankList();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

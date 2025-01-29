package org.wemeet.portal.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/util")
public class UtilRestController {

    @PostMapping("/games")
    public ResponseEntity<Void> postBoardGames() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

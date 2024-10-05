package org.koerber.consultlogger.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consults")
public class ConsultController {

    @GetMapping(produces = "application/json")
    public ResponseEntity<String> ping(){
        return new ResponseEntity<>("Hi", HttpStatus.OK);
    }
}

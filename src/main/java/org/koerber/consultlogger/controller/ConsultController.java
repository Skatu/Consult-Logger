package org.koerber.consultlogger.controller;

import org.koerber.consultlogger.dto.ConsultDTO;
import org.koerber.consultlogger.model.Consult;
import org.koerber.consultlogger.repository.ConsultRepository;
import org.koerber.consultlogger.service.ConsultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consults")
public class ConsultController {

    private ConsultService service;

    @Autowired
    public ConsultController(ConsultService service) {
        this.service = service;
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<String> create(@RequestBody ConsultDTO dto){
        service.create(dto);
        return new ResponseEntity<>("Hi", HttpStatus.OK);
    }
}

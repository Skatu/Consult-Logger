package org.koerber.consultlogger.controller;

import lombok.extern.slf4j.Slf4j;
import org.koerber.consultlogger.dto.ConsultDTO;
import org.koerber.consultlogger.exception.EntityNotFoundException;
import org.koerber.consultlogger.exception.InvalidSpecialtyException;
import org.koerber.consultlogger.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/consults")
public class ConsultController {

    private final ConsultService service;

    @Autowired
    public ConsultController(ConsultService service) {
        this.service = service;
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<?> create(@RequestBody ConsultDTO dto) {
        log.info("Create consult: {}", dto);
        try {
            var result = service.create(dto);
            log.info("Created consult: {}", result);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (EntityNotFoundException | InvalidSpecialtyException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}

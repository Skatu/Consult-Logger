package org.koerber.consultlogger.controller;

import org.koerber.consultlogger.dto.ConsultDTO;
import org.koerber.consultlogger.exception.EntityNotFoundException;
import org.koerber.consultlogger.exception.InvalidSpecialtyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consults")
public class ConsultController {

    private final ConsultService service;

    @Autowired
    public ConsultController(ConsultService service) {
        this.service = service;
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<?> create(@RequestBody ConsultDTO dto){
        try{
            var result = service.create(dto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (EntityNotFoundException | InvalidSpecialtyException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

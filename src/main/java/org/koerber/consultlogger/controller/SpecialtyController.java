package org.koerber.consultlogger.controller;

import org.koerber.consultlogger.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/specialties")
public class SpecialtyController {

    private final SpecialtyService service;

    @Autowired
    public SpecialtyController(SpecialtyService service) {
        this.service = service;
    }

    @GetMapping("/topSpecialties")
    public ResponseEntity<?> get(@RequestParam(defaultValue = "2") Integer numOfUniquePatients) {
        try {
            var result = service.getTopSpecialties(numOfUniquePatients);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}

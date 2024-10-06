package org.koerber.consultlogger.controller;

import lombok.extern.slf4j.Slf4j;
import org.koerber.consultlogger.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
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
        log.info("Init getTopSpecialties with numOfUniquePatients={}", numOfUniquePatients);
        try {
            var result = service.getTopSpecialties(numOfUniquePatients);
            log.info("Return result={}", result);
            return result.isEmpty()
                ? ResponseEntity.noContent().build()
                :ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}

package org.koerber.consultlogger.controller;

import lombok.extern.slf4j.Slf4j;
import org.koerber.consultlogger.exception.EntityNotFoundException;
import org.koerber.consultlogger.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService service;

    @Autowired
    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping("/{patientId}/consultsAndSymptoms")
    public ResponseEntity<?> getConsultsAndSymptoms(@PathVariable Long patientId) {
        log.info("Init Get consults and symptoms for patient {}", patientId);
        try {
            var result = service.getPatientConsultsAndSymptoms(patientId);
            log.info("Returning Get consults and symptoms for patient {}", result);
            return ResponseEntity.ok().body(result);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllPatients(@RequestParam(defaultValue = "0") Integer pageNumber,
                                            @RequestParam(defaultValue = "5") Integer pageSize,
                                            @RequestParam(defaultValue = "age") String sortBy,
                                            @RequestParam(defaultValue = "asc") String order,
                                            @RequestParam(required = false) String name,
                                            @RequestParam(required = false) Integer age) {
        log.info("Init Get all patients with params pageNumber={}, pageSize={}, sortBy={}, order={}, name={}, age={}", pageNumber, pageSize, sortBy, order, name, age);
        try {
            var paginationParams = new PaginationParams(pageNumber, pageSize, sortBy, order, name, age);
            var result = service.getAllPatients(paginationParams);
            log.info("Returning Get all patients with params {}", result);
            return ResponseEntity.ok().body(result);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}

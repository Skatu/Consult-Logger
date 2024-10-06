package org.koerber.consultlogger.controller;

import org.koerber.consultlogger.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private PatientService service;

    @Autowired
    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<Patient> getConsultsAndSymptoms(@PathVariable int patientId) {
        return new ResponseEntity(new Patient("wswr"), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Patient> getAllPatients() {
        return new ResponseEntity(service.getAllPatients(), HttpStatus.OK);
    }
}

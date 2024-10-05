package org.koerber.consultlogger.repository;

import org.koerber.consultlogger.model.Doctor;
import org.koerber.consultlogger.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}

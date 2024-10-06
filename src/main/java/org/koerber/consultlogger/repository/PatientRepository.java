package org.koerber.consultlogger.repository;

import org.koerber.consultlogger.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Page<Patient> findByName(String name, Pageable pageable);

    Page<Patient> findByAge(Integer age, Pageable pageable);
}

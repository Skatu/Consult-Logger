package org.koerber.consultlogger.repository;

import org.koerber.consultlogger.model.Consult;
import org.koerber.consultlogger.model.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SymptomRepository extends JpaRepository<Symptom, Long> {
}

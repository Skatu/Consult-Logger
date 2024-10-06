package org.koerber.consultlogger.repository;

import org.koerber.consultlogger.model.Pathology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PathologyRepository extends JpaRepository<Pathology, Long> {
}

package org.koerber.consultlogger.repository;

import org.koerber.consultlogger.model.Consult;
import org.koerber.consultlogger.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ConsultRepository extends JpaRepository<Consult, Long> {

    Optional<Collection<Consult>> findByPatient(Patient patient);

    @Query("SELECT s.name, COUNT(DISTINCT c.patient.id) AS numberOfPatients " +
            "FROM Consult c JOIN c.specialty s " +
            "GROUP BY s.name " +
            "HAVING COUNT(DISTINCT c.patient.id) > :numOfUniquePatients")
    Optional<Collection<Object[]>> getTopSpecialties(int numOfUniquePatients);
}

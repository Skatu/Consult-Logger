package org.koerber.consultlogger.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.koerber.consultlogger.dto.ConsultDTO;
import org.koerber.consultlogger.exception.InvalidSpecialtyException;

@Entity
@Getter
@Setter
public class Consult {
    @Id
    @GeneratedValue
    // using Long instead of other alternatives (i.e. UUID) for performance reasons,
    // as well as being more memory efficient and given it's a 1database, non-distributed project
    private Long id;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Specialty specialty;
    @ManyToOne
    private Pathology pathology;

    //date is missing

    protected Consult() {
    }

    public Consult(Long id, Doctor doctor, Patient patient, Pathology pathology) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.specialty = doctor.getSpecialty();
        this.pathology = pathology;
    }

    public Consult(Doctor doctor, Patient patient, Specialty specialty) throws InvalidSpecialtyException {
        this.doctor = doctor;
        this.patient = patient;
        if(!doctor.hasSpecialty(specialty)){
            throw new InvalidSpecialtyException("Doctor not assigned to specialty");
        }
        this.specialty = doctor.getSpecialty();
    }

    public ConsultDTO toDTO() {
        return new ConsultDTO(this.id, this.doctor.getId(), this.patient.getId(), this.specialty.getId());
    }
}

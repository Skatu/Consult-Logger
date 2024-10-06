package org.koerber.consultlogger.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.koerber.consultlogger.dto.ConsultDTO;
import org.koerber.consultlogger.dto.ConsultsSymptomsDTO;
import org.koerber.consultlogger.exception.InvalidSpecialtyException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Consult {
    @Id
    @GeneratedValue
    // using Long instead of other alternatives (i.e. UUID) for performance reasons,
    // as well as being more memory efficient and given it's a 1 database, non-distributed project
    private Long id;
    @ManyToOne
    @NonNull
    private Doctor doctor;
    @ManyToOne
    @NonNull
    private Patient patient;
    @ManyToOne
    @NonNull
    private Specialty specialty;
    @ManyToOne
    private Pathology pathology;
    @ManyToMany
    private List<Symptom> symptoms = new ArrayList<>();

    protected Consult() {
    }

    public Consult(Long id, @NonNull Doctor doctor, @NonNull Patient patient, @NonNull Pathology pathology) {
        this.id = id;
        this.pathology = pathology;
        this.doctor = doctor;
        this.patient = patient;
        this.specialty = doctor.getSpecialty();

    }

    public Consult(@NonNull Doctor doctor, @NonNull Patient patient) {
        this.doctor = doctor;
        this.patient = patient;
        this.specialty = doctor.getSpecialty();
    }

    public ConsultDTO toDTO() {
        return new ConsultDTO(this.id, this.doctor.getId(), this.patient.getId(), this.specialty.getId());
    }

    public ConsultsSymptomsDTO toConsultsSymptomsDTO() {
        var symptomsDTOs = this.symptoms.stream().map(Symptom::toDTO).toList();
        return new ConsultsSymptomsDTO(this.id, this.doctor.getName(), this.getSpecialty().getName(), symptomsDTOs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consult consult = (Consult) o;
        return Objects.equals(id, consult.id) && Objects.equals(doctor, consult.doctor) && Objects.equals(patient, consult.patient) && Objects.equals(specialty, consult.specialty) && Objects.equals(pathology, consult.pathology);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctor, patient, specialty, pathology);
    }
}

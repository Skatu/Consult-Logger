package org.koerber.consultlogger.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Consult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // using Long instead of other alternatives (i.e. UUID) for performance reasons,
    // as well as being more memory efficient and given it's a 1db project
    private Long id;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Specialty specialty;

    protected Consult() {
    }

    public Consult(Doctor doctor, Patient patient, Specialty specialty) {
        this.doctor = doctor;
        this.patient = patient;
        this.specialty = specialty;
    }


}

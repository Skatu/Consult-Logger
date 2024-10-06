package org.koerber.consultlogger.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToOne
    private Specialty specialty;

    protected Doctor() {
    }

    public Doctor(String name, Specialty specialty) {
        this.name = name;
        this.specialty = specialty;
    }

    public boolean hasSpecialty(Specialty specialty) {
        return this.specialty.equals(specialty);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id) && Objects.equals(name, doctor.name) && Objects.equals(specialty, doctor.specialty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, specialty);
    }
}

package org.koerber.consultlogger.model;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    @OneToOne
    @NonNull
    private Specialty specialty;

    protected Doctor() {
    }

    public Doctor(String name, @NonNull Specialty specialty) {
        if(StringUtils.isBlank(name)){
            throw new IllegalArgumentException("Name cannot be blank");
        }
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

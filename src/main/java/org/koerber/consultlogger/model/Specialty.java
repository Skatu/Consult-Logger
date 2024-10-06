package org.koerber.consultlogger.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
public class Specialty {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    protected Specialty() {
    }

    public Specialty(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialty specialty = (Specialty) o;
        return Objects.equals(id, specialty.id) && Objects.equals(name, specialty.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

package org.koerber.consultlogger.model;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
public class Specialty {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;

    protected Specialty() {
    }

    public Specialty(Long id, String name) {
        this(name);
        this.id = id;
    }

    public Specialty(String name) {
        if(StringUtils.isBlank(name)){
            throw new IllegalArgumentException("Name cannot be blank");
        }
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

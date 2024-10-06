package org.koerber.consultlogger.model;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Pathology {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    @Column(unique = true)
    @OneToMany
    private List<Symptom> symptoms = new ArrayList<>();

    protected Pathology() {
    }

    public Pathology(String name) {
        if(StringUtils.isBlank(name)){
            throw new IllegalArgumentException("Name cannot be blank");
        }
        this.name = name;
    }

    public Pathology(String name, List<Symptom> symptoms) {
        this(name);
        this.symptoms = symptoms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pathology pathology = (Pathology) o;
        return Objects.equals(id, pathology.id) && Objects.equals(name, pathology.name) && Objects.equals(symptoms, pathology.symptoms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, symptoms);
    }
}

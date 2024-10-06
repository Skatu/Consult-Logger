package org.koerber.consultlogger.model;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.koerber.consultlogger.dto.PatientDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Patient {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    private int age;
    @ManyToMany
    @NonNull
    private List<Pathology> pathologies = new ArrayList<>();

    protected Patient() {
    }

    public Patient(String name, int age, List<Pathology> pathologies) {
        if(StringUtils.isBlank(name)){
            throw new IllegalArgumentException("Name cannot be blank");
        }
        this.name = name;
        this.age = age;
        this.pathologies = pathologies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return age == patient.age && Objects.equals(id, patient.id) && Objects.equals(name, patient.name) && Objects.equals(pathologies, patient.pathologies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, pathologies);
    }

    public PatientDTO toDTO() {
        var pathologiesNames = this.pathologies.stream().map(Pathology::getName).toList();
        return new PatientDTO(this.name, this.age, pathologiesNames);
    }
}

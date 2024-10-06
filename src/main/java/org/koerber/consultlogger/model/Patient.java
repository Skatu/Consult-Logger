package org.koerber.consultlogger.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Patient {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;
    @ManyToMany
    private List<Pathology> pathologies = new ArrayList<>(); //FIXME Should patient have this list???

    protected Patient() {
    }

    public Patient(String name) {
        this.name = name;
    }

    public Patient(String name, int age, List<Pathology> pathologies) {
        this.name = name;
        this.age = age;
        this.pathologies = pathologies;
    }
}

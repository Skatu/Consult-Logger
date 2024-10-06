package org.koerber.consultlogger.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Pathology {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(unique = true)
    @OneToMany /*(cascade = CascadeType.ALL)*/
    private List<Symptom> symptoms = new ArrayList<>();

    protected Pathology() {
    }

    public Pathology(String name) {
        this.name = name;
    }

    public Pathology(String name, List<Symptom> symptoms) {
        this.name = name;
        this.symptoms = symptoms;
    }



    public boolean addSymptom(Symptom symptom) {
        if(symptom == null || symptoms.contains(symptom)){
            return false;
        }
        return symptoms.add(symptom);
    }
}

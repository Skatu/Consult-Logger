package org.koerber.consultlogger.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Symptom {
    @Id
    @GeneratedValue
    private Long id;
    private String description;

    protected Symptom() {
    }

    public Symptom(String description) {
        this.description = description;
    }
}

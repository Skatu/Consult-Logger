package org.koerber.consultlogger.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Specialty {
    @Id
    private Long id;

    private String name;

    public Specialty(String name) {
        this.name = name;
    }
}

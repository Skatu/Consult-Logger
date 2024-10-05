package org.koerber.consultlogger.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patient {
    @Id
    private Long id;
    private String name;
    private int age;
    @ManyToOne
    private Pathology pathology;
}

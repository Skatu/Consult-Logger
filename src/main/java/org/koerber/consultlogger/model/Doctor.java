package org.koerber.consultlogger.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Doctor {

    @Id
    private Long id;
    private String name;
    @OneToOne
    private Specialty doctor;
}

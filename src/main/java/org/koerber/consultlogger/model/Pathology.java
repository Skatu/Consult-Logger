package org.koerber.consultlogger.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Pathology {
    @Id
    private Long id;
    @Column(unique = true)
    @OneToMany
    private List<Symptom> symptoms;
}

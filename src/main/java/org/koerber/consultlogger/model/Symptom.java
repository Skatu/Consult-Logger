package org.koerber.consultlogger.model;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.koerber.consultlogger.dto.SymptomDTO;

import java.util.Objects;

@Entity
@Getter
@Setter
public class Symptom {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String description;

    protected Symptom() {
    }

    public Symptom(String description) {
        if(StringUtils.isBlank(description)){
            throw new IllegalArgumentException("Description cannot be blank");
        }
        this.description = description;
    }

    public SymptomDTO toDTO() {
        return new SymptomDTO(id, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symptom symptom = (Symptom) o;
        return Objects.equals(id, symptom.id) && Objects.equals(description, symptom.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }
}

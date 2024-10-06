package org.koerber.consultlogger.dto;

import java.util.List;

public record PatientDTO(String name, Integer age, List<String> pathologies) {
}

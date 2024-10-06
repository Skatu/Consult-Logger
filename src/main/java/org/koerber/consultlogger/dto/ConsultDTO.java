package org.koerber.consultlogger.dto;

public record ConsultDTO(Long id, Long doctorId, Long patientId,
                         Long specialtyId) {

}

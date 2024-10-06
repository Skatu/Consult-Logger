package org.koerber.consultlogger.dto;

import java.util.List;

public record ConsultsSymptomsDTO(Long consultId, String doctorName, String specialtyName, List<SymptomDTO> symptoms) {

}

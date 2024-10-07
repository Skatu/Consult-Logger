package org.koerber.consultlogger.service;

import org.koerber.consultlogger.pagination.PaginationParams;
import org.koerber.consultlogger.dto.ConsultsSymptomsDTO;
import org.koerber.consultlogger.dto.PatientDTO;
import org.koerber.consultlogger.exception.ConsultNotFoundException;
import org.koerber.consultlogger.exception.PatientNotFoundException;

import java.util.Collection;

public interface PatientService {

    Collection<ConsultsSymptomsDTO> getPatientConsultsAndSymptoms(Long patientId) throws PatientNotFoundException, ConsultNotFoundException;

    Collection<PatientDTO> getAllPatients(PaginationParams paginationParams);
}

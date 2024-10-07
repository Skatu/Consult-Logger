package org.koerber.consultlogger.service;

import lombok.extern.slf4j.Slf4j;
import org.koerber.consultlogger.pagination.PaginationParams;
import org.koerber.consultlogger.dto.ConsultsSymptomsDTO;
import org.koerber.consultlogger.dto.PatientDTO;
import org.koerber.consultlogger.exception.ConsultNotFoundException;
import org.koerber.consultlogger.exception.PatientNotFoundException;
import org.koerber.consultlogger.model.Consult;
import org.koerber.consultlogger.model.Patient;
import org.koerber.consultlogger.repository.ConsultRepository;
import org.koerber.consultlogger.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final ConsultRepository consultRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, ConsultRepository consultRepository) {
        this.patientRepository = patientRepository;
        this.consultRepository = consultRepository;
    }

    public Collection<ConsultsSymptomsDTO> getPatientConsultsAndSymptoms(Long patientId) throws PatientNotFoundException, ConsultNotFoundException {
        log.info("Calling getPatientConsultsAndSymptoms with patient id {}", patientId);
        var patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(patientId));
        log.info("Patient found. Getting all consults and symptoms for patient id {}", patientId);
        var consults = consultRepository.findByPatient(patient)
                .orElseThrow(() -> new ConsultNotFoundException("Could not find consult for patient with id: " + patientId));
        log.info("Consults found for patient with id {}", patientId);
        return consults.stream().map(Consult::toConsultsSymptomsDTO).toList();
    }

    @Override
    public Collection<PatientDTO> getAllPatients(PaginationParams paginationParams) {
        log.info("Calling getAllPatients with paginationParams {}", paginationParams);
        var sort = paginationParams.isSortOrderAsc()
                ? Sort.by(paginationParams.sortBy()).ascending()
                : Sort.by(paginationParams.sortBy()).descending();
        var pageRequest = PageRequest.of(paginationParams.pageNumber(),
                paginationParams.pageSize(), sort);
        if (paginationParams.name() != null) {
            log.info("Calling repository method findByName");
            return patientRepository.findByName(paginationParams.name(), pageRequest).stream().map(Patient::toDTO).toList();
        } else if (paginationParams.age() != null) {
            log.info("Calling repository method findByAge");
            return patientRepository.findByAge(paginationParams.age(), pageRequest).stream().map(Patient::toDTO).toList();
        }else{
            log.info("Calling repository method findAll");
            return patientRepository.findAll(pageRequest).stream().map(Patient::toDTO).toList();
        }
    }
}

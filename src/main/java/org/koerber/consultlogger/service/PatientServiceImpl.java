package org.koerber.consultlogger.service;

import org.koerber.consultlogger.controller.PaginationParams;
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
        var patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(patientId));
        var consult = consultRepository.findByPatient(patient)
                .orElseThrow(() -> new ConsultNotFoundException("Could not find consult for patient with id: " + patientId));
        return consult.stream().map(Consult::toConsultsSymptomsDTO).toList();
    }

    @Override
    public Collection<PatientDTO> getAllPatients(PaginationParams paginationParams) {
        var sort = paginationParams.isSortOrderAsc()
                ? Sort.by(paginationParams.sortBy()).ascending()
                : Sort.by(paginationParams.sortBy()).descending();
        var pageRequest = PageRequest.of(paginationParams.pageNumber(),
                paginationParams.pageSize(), sort);
        if (paginationParams.name() != null) {
            return patientRepository.findByName(paginationParams.name(), pageRequest).stream().map(Patient::toDTO).toList();
        } else if (paginationParams.age() != null) {
            return patientRepository.findByAge(paginationParams.age(), pageRequest).stream().map(Patient::toDTO).toList();
        }
        return patientRepository.findAll(pageRequest).stream().map(Patient::toDTO).toList();
    }
}

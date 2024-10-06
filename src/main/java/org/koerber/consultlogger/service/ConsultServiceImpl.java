package org.koerber.consultlogger.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.koerber.consultlogger.dto.ConsultDTO;
import org.koerber.consultlogger.exception.DoctorNotFoundException;
import org.koerber.consultlogger.exception.InvalidSpecialtyException;
import org.koerber.consultlogger.exception.PatientNotFoundException;
import org.koerber.consultlogger.model.Consult;
import org.koerber.consultlogger.repository.ConsultRepository;
import org.koerber.consultlogger.repository.DoctorRepository;
import org.koerber.consultlogger.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class ConsultServiceImpl implements ConsultService {

    private final ConsultRepository consultRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public ConsultServiceImpl(ConsultRepository consultRepository, DoctorRepository doctorRepository,
                              PatientRepository patientRepository) {
        this.consultRepository = consultRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional
    public ConsultDTO create(ConsultDTO dto) throws DoctorNotFoundException, PatientNotFoundException, InvalidSpecialtyException {
        log.info("Creating consult: {}", dto);
        var consultToCreate = mapConsultDTOToConsult(dto);
        log.info("Saving Consult: {}", consultToCreate);
        var result = consultRepository.save(consultToCreate);
        log.info("Consult Saved: {}", result);
        return result.toDTO();
    }

    private Consult mapConsultDTOToConsult(ConsultDTO dto) throws DoctorNotFoundException,
            PatientNotFoundException, InvalidSpecialtyException {
        log.info("Mapping consultDTO to Consult object: {}" +
                "\nGetting doctor with id: {}", dto, dto.doctorId());
        var doctor = doctorRepository.findById(dto.doctorId())
                .orElseThrow(() -> new DoctorNotFoundException(dto.doctorId()));
        if (!doctor.getSpecialty().getId().equals(dto.specialtyId())) {
            log.error("Consult Specialty {} does not belong to Doctor {}", dto.specialtyId(), doctor.getId());
            throw new InvalidSpecialtyException("Consult Specialty "+ dto.specialtyId() +" does not belong to Doctor "+doctor.getId());
        }
        log.info("Getting patient with id: {}", dto.patientId());
        var patient = patientRepository.findById(dto.patientId())
                .orElseThrow(() -> new PatientNotFoundException(dto.patientId()));
        return new Consult(doctor, patient);
    }
}

package org.koerber.consultlogger.service;

import jakarta.transaction.Transactional;
import org.koerber.consultlogger.dto.ConsultDTO;
import org.koerber.consultlogger.exception.DoctorNotFoundException;
import org.koerber.consultlogger.exception.InvalidSpecialtyException;
import org.koerber.consultlogger.exception.PatientNotFoundException;
import org.koerber.consultlogger.exception.SpecialtyNotFoundException;
import org.koerber.consultlogger.model.Consult;
import org.koerber.consultlogger.repository.ConsultRepository;
import org.koerber.consultlogger.repository.DoctorRepository;
import org.koerber.consultlogger.repository.PatientRepository;
import org.koerber.consultlogger.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultServiceImpl implements ConsultService {

    private final ConsultRepository consultRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final SpecialtyRepository specialtyRepository;

    @Autowired
    public ConsultServiceImpl(ConsultRepository consultRepository, DoctorRepository doctorRepository,
                              PatientRepository patientRepository, SpecialtyRepository specialtyRepository) {
        this.consultRepository = consultRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    @Transactional
    public ConsultDTO create(ConsultDTO dto) throws DoctorNotFoundException, SpecialtyNotFoundException, PatientNotFoundException, InvalidSpecialtyException {
        var consultToCreate = mapConsultDTOToConsult(dto);
        var result = consultRepository.save(consultToCreate);
        return result.toDTO();
    }

    private Consult mapConsultDTOToConsult(ConsultDTO dto) throws DoctorNotFoundException,
            SpecialtyNotFoundException, PatientNotFoundException, InvalidSpecialtyException {
        var doctor = doctorRepository.findById(dto.doctorId())
                .orElseThrow(() -> new DoctorNotFoundException(dto.doctorId()));
        var specialty = specialtyRepository.findById(dto.specialtyId())
                .orElseThrow(() -> new SpecialtyNotFoundException(dto.specialtyId()));
        var patient = patientRepository.findById(dto.patientId())
                .orElseThrow(() -> new PatientNotFoundException(dto.patientId()));
        return new Consult(doctor, patient, specialty);
    }
}

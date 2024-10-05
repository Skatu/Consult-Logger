package org.koerber.consultlogger.service;

import org.koerber.consultlogger.controller.ConsultService;
import org.koerber.consultlogger.dto.ConsultDTO;
import org.koerber.consultlogger.model.Consult;
import org.koerber.consultlogger.repository.ConsultRepository;
import org.koerber.consultlogger.repository.DoctorRepository;
import org.koerber.consultlogger.repository.PatientRepository;
import org.koerber.consultlogger.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultServiceImpl implements ConsultService {
    //TODO Logs
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
    public void create(ConsultDTO dto) {
        Consult consult = convertDTOToConsult(dto);
        consultRepository.save(consult);
    }

    private Consult convertDTOToConsult(ConsultDTO dto) {
        var doctor = doctorRepository.findById(dto.doctorId());
        if(doctor.isEmpty()) {
            throw new IllegalArgumentException("Doctor not found");
        }
        var patient = patientRepository.findById(dto.patientId());
        if(patient.isEmpty()) {
            //TODO: maybe create a new patient instead if they dont exist?
            throw new IllegalArgumentException("Patient not found");
        }
        var specialty = specialtyRepository.findById(dto.specialtyId());
        if(specialty.isEmpty()) {
            throw new IllegalArgumentException("Specialty not found");
        }
        return new Consult(doctor.get(), patient.get(), specialty.get());
    }
}

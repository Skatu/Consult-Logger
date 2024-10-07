package org.koerber.consultlogger.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.koerber.consultlogger.dto.ConsultDTO;
import org.koerber.consultlogger.exception.DoctorNotFoundException;
import org.koerber.consultlogger.exception.InvalidSpecialtyException;
import org.koerber.consultlogger.exception.PatientNotFoundException;
import org.koerber.consultlogger.model.Consult;
import org.koerber.consultlogger.model.Doctor;
import org.koerber.consultlogger.model.Patient;
import org.koerber.consultlogger.model.Specialty;
import org.koerber.consultlogger.repository.DoctorRepository;
import org.koerber.consultlogger.repository.PatientRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultServiceImplTest {

    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private PatientRepository patientRepository;
    @InjectMocks
    private ConsultServiceImpl consultService;

    private ConsultDTO consultDTO;
    private Doctor doctor;
    private Patient patient;

    @BeforeEach
    void setUp() {
        consultDTO = new ConsultDTO(1L, 1L, 2L, 3L);
        var specialty = new Specialty(3L,"Test1");
        doctor = new Doctor("Test",specialty);
        doctor.setId(1L);
        doctor.setSpecialty(specialty);
        patient = new Patient("Test",1, Collections.emptyList());
        patient.setId(2L);
    }

    @Test
    void successOnMapConsultDTOToConsult() throws DoctorNotFoundException, PatientNotFoundException, InvalidSpecialtyException {
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(patientRepository.findById(2L)).thenReturn(Optional.of(patient));
        Consult result = consultService.mapConsultDTOToConsult(consultDTO);
        assertNotNull(result);
        assertEquals(doctor, result.getDoctor());
        assertEquals(patient, result.getPatient());
        verify(doctorRepository).findById(1L);
        verify(patientRepository).findById(2L);
    }

    @Test
    void DoctorNotFoundOnMapConsultDTOToConsult() {
        when(doctorRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(DoctorNotFoundException.class, () -> consultService.mapConsultDTOToConsult(consultDTO));
        verify(patientRepository, never()).findById(anyLong());
    }

    @Test
    void PatientNotFoundOnMapConsultDTOToConsult() {
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(patientRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(PatientNotFoundException.class, () -> consultService.mapConsultDTOToConsult(consultDTO));
        verify(doctorRepository).findById(1L);
        verify(patientRepository).findById(2L);
    }

    @Test
    void InvalidSpecialtyOnMapConsultDTOToConsult() {
        var invalidSpecialty = new Specialty("Test2");
        invalidSpecialty.setId(99L);
        doctor.setSpecialty(invalidSpecialty);
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        assertThrows(InvalidSpecialtyException.class, () -> consultService.mapConsultDTOToConsult(consultDTO));
        verify(doctorRepository).findById(1L);
        verify(patientRepository, never()).findById(anyLong());
    }
}

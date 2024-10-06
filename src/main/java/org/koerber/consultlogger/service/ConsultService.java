package org.koerber.consultlogger.service;

import org.koerber.consultlogger.dto.ConsultDTO;
import org.koerber.consultlogger.exception.DoctorNotFoundException;
import org.koerber.consultlogger.exception.InvalidSpecialtyException;
import org.koerber.consultlogger.exception.PatientNotFoundException;
import org.koerber.consultlogger.exception.SpecialtyNotFoundException;

public interface ConsultService {

    ConsultDTO create(ConsultDTO dto) throws DoctorNotFoundException, SpecialtyNotFoundException, PatientNotFoundException, InvalidSpecialtyException;
}

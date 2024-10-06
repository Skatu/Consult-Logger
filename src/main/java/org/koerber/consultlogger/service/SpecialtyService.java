package org.koerber.consultlogger.service;

import org.koerber.consultlogger.dto.TopSpecialtiesDTO;
import org.koerber.consultlogger.exception.SpecialtyException;

import java.util.Collection;

public interface SpecialtyService {
    Collection<TopSpecialtiesDTO> getTopSpecialties(int numOfUniquePatients) throws SpecialtyException;
}

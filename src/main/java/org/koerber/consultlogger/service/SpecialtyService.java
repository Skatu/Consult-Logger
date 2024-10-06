package org.koerber.consultlogger.service;

import org.koerber.consultlogger.dto.TopSpecialtiesDTO;

import java.util.Collection;

public interface SpecialtyService {
    Collection<TopSpecialtiesDTO> getTopSpecialties(int numOfUniquePatients);
}

package org.koerber.consultlogger.service;

import org.koerber.consultlogger.dto.TopSpecialtiesDTO;
import org.koerber.consultlogger.repository.ConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {
    private final ConsultRepository consultRepository;

    @Autowired
    public SpecialtyServiceImpl(ConsultRepository consultRepository) {
        this.consultRepository = consultRepository;
    }

    @Override
    public Collection<TopSpecialtiesDTO> getTopSpecialties(int numOfUniquePatients) {
        var res = consultRepository.getTopSpecialties(numOfUniquePatients).get();
        return res.stream()
                .map(o -> new TopSpecialtiesDTO((String) o[0], (Long) o[1])).toList();
    }
}

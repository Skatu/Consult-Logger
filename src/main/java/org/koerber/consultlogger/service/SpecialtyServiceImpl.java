package org.koerber.consultlogger.service;

import lombok.extern.slf4j.Slf4j;
import org.koerber.consultlogger.dto.TopSpecialtiesDTO;
import org.koerber.consultlogger.exception.SpecialtyException;
import org.koerber.consultlogger.repository.ConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
public class SpecialtyServiceImpl implements SpecialtyService {
    private final ConsultRepository consultRepository;

    @Autowired
    public SpecialtyServiceImpl(ConsultRepository consultRepository) {
        this.consultRepository = consultRepository;
    }

    @Override
    public Collection<TopSpecialtiesDTO> getTopSpecialties(int numOfUniquePatients) throws SpecialtyException {
        log.info("Calling getTopSpecialties with numOfUniquePatients={}", numOfUniquePatients);
        var res = consultRepository.getTopSpecialties(numOfUniquePatients).orElseThrow(() ->
                new SpecialtyException("An error occurred while getting top specialties"));
        log.info("Top specialties found: {}", res);
        return res.stream()
                .map(o -> new TopSpecialtiesDTO((String) o[0], (Long) o[1])).toList();
    }
}

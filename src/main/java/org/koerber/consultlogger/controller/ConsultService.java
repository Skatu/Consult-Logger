package org.koerber.consultlogger.controller;

import org.koerber.consultlogger.dto.ConsultDTO;
import org.koerber.consultlogger.model.Consult;

public interface ConsultService {

    void create(ConsultDTO dto);
}

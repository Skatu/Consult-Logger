package org.koerber.consultlogger.controller;

import org.koerber.consultlogger.model.Patient;

import java.util.List;

public interface PatientService {



    //FIXME change to dto and add filtering/pagination
    List<Patient> getAllPatients();

}

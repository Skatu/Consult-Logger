package org.koerber.consultlogger.exception;

public class PatientNotFoundException extends EntityNotFoundException {

    public PatientNotFoundException(Long id) {
        super("Patient", id);
    }
}

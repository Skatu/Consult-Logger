package org.koerber.consultlogger.exception;

public class SpecialtyNotFoundException extends EntityNotFoundException {

    public SpecialtyNotFoundException(Long id) {
        super("Specialty", id);
    }
}

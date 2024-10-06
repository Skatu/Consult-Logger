package org.koerber.consultlogger.exception;

public class DoctorNotFoundException extends EntityNotFoundException {

    public DoctorNotFoundException(Long id){
        super("Doctor", id);
    }
}

package org.koerber.consultlogger.exception;

public class EntityNotFoundException extends Exception{

    public EntityNotFoundException(String entityName, Long id){
        super(entityName+" with id " + id + " not found");
    }
}

package com.Pulson.spring_security_swagger_jackson.swagger.exception_handler;

public class EmptyEntitiesListException extends RuntimeException {
    public EmptyEntitiesListException(Class<?> entity) {
        super("No " + entity.getSimpleName().toLowerCase() + "s were found!");
    }
}

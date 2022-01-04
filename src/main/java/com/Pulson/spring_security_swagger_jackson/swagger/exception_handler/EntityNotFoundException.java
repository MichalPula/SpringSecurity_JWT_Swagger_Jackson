package com.Pulson.spring_security_swagger_jackson.swagger.exception_handler;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class<?> entity, Long orderId) {
        super(entity.getSimpleName() + " with id = "  + orderId + " was not found");
    }
}

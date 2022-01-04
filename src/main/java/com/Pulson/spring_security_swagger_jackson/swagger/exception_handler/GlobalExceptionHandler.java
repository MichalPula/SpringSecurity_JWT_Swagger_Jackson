package com.Pulson.spring_security_swagger_jackson.swagger.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            EntityNotFoundException.class,
            EmptyEntitiesListException.class})
    public ResponseEntity<ErrorMessage> handleEntityNotFound(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorMessage(LocalDateTime.now(), HttpStatus.NOT_FOUND.toString(), exception.getMessage()));
    }

}

package com.Pulson.spring_security_swagger_jackson.swagger.exception_handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime timestamp;
    private String status;
    private String message;
}

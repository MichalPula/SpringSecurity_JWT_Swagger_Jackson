package com.Pulson.spring_security_swagger_jackson.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JsonLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        String dateString = localDateTime.format(formatter);
        jsonGenerator.writeString(dateString);
    }
}

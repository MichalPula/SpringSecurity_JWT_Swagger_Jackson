package com.Pulson.spring_security_swagger_jackson.jackson.deserializing_string_and_integer_for_fun;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class JsonStringDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String string = node.toString();

        return string.toUpperCase();
    }
}

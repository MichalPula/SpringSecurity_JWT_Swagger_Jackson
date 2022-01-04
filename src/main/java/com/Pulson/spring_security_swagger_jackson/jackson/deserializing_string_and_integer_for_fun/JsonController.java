package com.Pulson.spring_security_swagger_jackson.jackson.deserializing_string_and_integer_for_fun;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    private static class MyNumber {
        @JsonDeserialize(using = JsonStringDeserializer.class)
        private String spelling;

        @JsonDeserialize(using = JsonIntegerDeserializer.class)
        private Integer number;
    }


    @PostMapping(value = "/deserialize")
    public void testIntegerDeserializer(@RequestBody MyNumber number) {
        System.out.println(number);
    }
    //Każdy String oznaczony @JsonDeserialize(using = JsonStringDeserializer.class) zostanie zmieniony .toUpperCase
    //Każdy Integer oznaczony @JsonDeserialize(using = JsonIntegerDeserializer.class) zostanie pomnożony x2 w Deserializerze
//    {
//        "spelling": "ten",
//            "number": 10
//    }
    //Zwróci JsonController.MyNumber(spelling="TEN", number=20)
}

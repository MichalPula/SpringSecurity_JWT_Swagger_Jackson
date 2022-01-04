package com.Pulson.spring_security_swagger_jackson.jackson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Student {

    private String name;

    private Integer age;

    private List<String> favouriteSports;
                                                          //hh = 12 hour format
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss dd-MM-yyyy")
    //@JsonSerialize(using = JsonLocalDateTimeSerializer.class)
    //@JsonDeserialize(using = JsonLocalDateTimeDeserializer.class)
    private LocalDateTime birthTime;
}

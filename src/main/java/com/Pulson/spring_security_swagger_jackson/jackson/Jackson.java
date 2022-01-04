package com.Pulson.spring_security_swagger_jackson.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class Jackson {

    private final static Student STUDENT = new Student("Joe Mama", 23, Arrays.asList("Soccer", "Basketball"),
            LocalDateTime.of(1998, 12, 24, 13, 22, 55));

    private final ObjectMapper objectMapper;

    private final String PATH = "src/main/java/com/pulson/spring_security_swagger_jackson/jackson/student_jackson.json";

    @Autowired
    public Jackson(@Qualifier(value = "jacksonObjectMapper") ObjectMapper objectMapper) throws Exception {
        this.objectMapper = objectMapper;
        jackson();
    }

    private void jackson() throws Exception {
        //WRITE
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(PATH), STUDENT);
        //{
        //  "name" : "Joe Mama",
        //  "age" : 23,
        //  "favouriteSports" : [ "Soccer", "Basketball" ],
        //  "birthTime" : "13:22:55 24-12-1998"
        //}
        objectMapper.writeValue(new File(PATH), STUDENT);
        //{"name":"Joe Mama","age":23,"favouriteSports":["Soccer","Basketball"],"birthTime":"13:22:55 24-12-1998"}

        String studentJSONString = objectMapper.writeValueAsString(STUDENT);
        //{"name":"Joe Mama","age":23,"favouriteSports":["Soccer","Basketball"],"birthTime":"13:22:55 24-12-1998"}


        //READ
        Student student = objectMapper.readValue(new File(PATH), Student.class);
        //Student(name=Joe Mama, age=23, favouriteSports=[Soccer, Basketball], birthTime=1998-12-24T13:22:55)

        Student student2 = objectMapper.readValue(studentJSONString, Student.class);
        //Student(name=Joe Mama, age=23, favouriteSports=[Soccer, Basketball], birthTime=1998-12-24T13:22:55)
    }
}

package com.Pulson.spring_security_swagger_jackson.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class JSONConfig {

    @Bean
    @Primary
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
                 //WebApplicationContext.SCOPE_REQUEST
    public ObjectMapper jacksonObjectMapper() {
        //ten objectMapper używany jest globalnie. Spring używa domyślnego JacksonHttpMessageConvertersConfiguration
        // lecz gdy zobaczy nasz ObjectMapper - zaczyna z niego korzystać
        //MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        //	  return new MappingJackson2HttpMessageConverter(objectMapper);
        //}
        ObjectMapper objectMapper = new ObjectMapper();

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy")));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy")));
                                                                                                                //"19:58:47 24-11-2021"
        objectMapper.registerModule(javaTimeModule);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

//        SimpleModule integerModule = new SimpleModule();
//        integerModule.addDeserializer(Integer.class, new JsonIntegerDeserializer());
//        objectMapper.registerModule(integerModule);

        objectMapper.findAndRegisterModules();//java.Time won't work without it!

        return objectMapper;
    }



    @Bean
    public ObjectMapper usaTimeMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss a")));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss a")));
                                                                                                                //"11-24-2021 07:58:06 PM"
        objectMapper.registerModule(javaTimeModule);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        objectMapper.findAndRegisterModules();

        return objectMapper;
    }
}

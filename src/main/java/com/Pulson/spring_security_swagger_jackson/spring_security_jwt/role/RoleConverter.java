package com.Pulson.spring_security_swagger_jackson.spring_security_jwt.role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RoleConverter implements AttributeConverter<Role, String> {

    @Override
    public String convertToDatabaseColumn(Role role) {
        return switch(role) {
            case ROLE_ADMIN -> "Admin";
            case ROLE_MANAGER -> "Manager";
            case ROLE_USER -> "User";
        };
    }

    @Override
    public Role convertToEntityAttribute(String string) {
        return switch (string) {
            case "Admin" -> Role.ROLE_ADMIN;
            case "User" -> Role.ROLE_USER;
            case "Manager" -> Role.ROLE_MANAGER;
            default -> throw new IllegalArgumentException("Unknown Role = " + string);
        };
    }
}

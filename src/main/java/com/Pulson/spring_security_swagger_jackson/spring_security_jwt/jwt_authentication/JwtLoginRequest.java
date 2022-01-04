package com.Pulson.spring_security_swagger_jackson.spring_security_jwt.jwt_authentication;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtLoginRequest {

    private String username;
    private String password;
}

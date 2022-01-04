package com.Pulson.spring_security_swagger_jackson.spring_security_jwt.jwt_authentication.filters;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt.constants")
@Getter
@Setter
public class JwtConstants {
    private String secret;
    private Long expiration_time;
    private String token_prefix;
    private String http_header_string;
}

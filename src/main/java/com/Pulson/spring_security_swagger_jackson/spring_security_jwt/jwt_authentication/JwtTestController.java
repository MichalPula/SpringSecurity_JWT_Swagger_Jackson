package com.Pulson.spring_security_swagger_jackson.spring_security_jwt.jwt_authentication;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtTestController {

    @GetMapping("/everyone")
    public String everyone(){
        return "Anyone can see this <3";
    }

    @GetMapping("/authenticated")
    public String authenticated(){
        return "Only for chosen ones";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Admin admin admin :)";
    }

    @GetMapping("/manager")
    public String manager(){
        return "Only manager's stuff. He can see you...";
    }
}

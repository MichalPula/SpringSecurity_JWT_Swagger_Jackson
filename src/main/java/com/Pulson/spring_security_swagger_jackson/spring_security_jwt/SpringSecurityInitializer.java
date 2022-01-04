package com.Pulson.spring_security_swagger_jackson.spring_security_jwt;

import com.Pulson.spring_security_swagger_jackson.spring_security_jwt.role.Role;
import com.Pulson.spring_security_swagger_jackson.spring_security_jwt.user.User;
import com.Pulson.spring_security_swagger_jackson.spring_security_jwt.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SpringSecurityInitializer {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SpringSecurityInitializer(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        initializeUsers();
    }

    private void initializeUsers() {
        User admin = new User("pulson", passwordEncoder.encode("pulson"), Role.ROLE_ADMIN);
        User manager = new User("manager", passwordEncoder.encode("manager"), Role.ROLE_MANAGER);
        User user = new User("joe", passwordEncoder.encode("mama"), Role.ROLE_USER);

        List<User> users = Arrays.asList(admin, manager, user);
        userRepository.saveAll(users);
    }
}

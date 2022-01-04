package com.Pulson.spring_security_swagger_jackson.spring_security_jwt.user;

import com.Pulson.spring_security_swagger_jackson.spring_security_jwt.role.Role;
import com.Pulson.spring_security_swagger_jackson.spring_security_jwt.role.RoleConverter;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "spring_security")
public class User {

    @Id
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @Column(name = "id", unique = true, updatable = false)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @NotNull
    private boolean isAccountActive = true;

    @NotNull
    @Convert(converter = RoleConverter.class)
    private Role role;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}

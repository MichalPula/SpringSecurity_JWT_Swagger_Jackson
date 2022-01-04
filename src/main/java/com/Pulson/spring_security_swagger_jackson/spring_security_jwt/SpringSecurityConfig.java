package com.Pulson.spring_security_swagger_jackson.spring_security_jwt;

import com.Pulson.spring_security_swagger_jackson.spring_security_jwt.jwt_authentication.UserDetailsServiceImpl;
import com.Pulson.spring_security_swagger_jackson.spring_security_jwt.jwt_authentication.filters.JwtAuthenticationFilter;
import com.Pulson.spring_security_swagger_jackson.spring_security_jwt.jwt_authentication.filters.JwtAuthorizationFilter;
import com.Pulson.spring_security_swagger_jackson.spring_security_jwt.jwt_authentication.filters.JwtConstants;
import com.Pulson.spring_security_swagger_jackson.spring_security_jwt.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(JwtConstants.class)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailServiceImpl;
    private final UserRepository userRepository;
    private final JwtConstants jwtConstants;

    @Autowired
    public SpringSecurityConfig(UserDetailsServiceImpl userDetailServiceImpl, UserRepository userRepository,
                                JwtConstants jwtConstants) {
        this.userDetailServiceImpl = userDetailServiceImpl;
        this.userRepository = userRepository;
        this.jwtConstants = jwtConstants;
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        authenticationProvider.setUserDetailsService(this.userDetailServiceImpl);
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()//cross site request forgery - mając autoryzację tokenem nie musimy się już bronić przed atakami
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//utrzymywanie sesji, gdy mamy token jest niepotrzebne
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), "/login", jwtConstants))//dodanie filtra zajmującego się logowaniem użytkowników -> autentykacja, sprawdzenie zgodności z bazą danych i nadanie im JWT Tokena
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userRepository, jwtConstants))//dodanie filtra autoryzującego przychodzące HTTP requesty. Metoda doFilterInternal sprwadza, czy użytkownik próbujący wykonać zapytanie dołączył do niego nagłówek z Tokenem
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/everyone").permitAll()
                .antMatchers("/authenticated").authenticated()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/manager").hasAnyRole("MANAGER", "ADMIN");
    }
}

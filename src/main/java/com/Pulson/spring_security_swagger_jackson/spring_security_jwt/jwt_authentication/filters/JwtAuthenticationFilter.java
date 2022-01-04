package com.Pulson.spring_security_swagger_jackson.spring_security_jwt.jwt_authentication.filters;

import com.Pulson.spring_security_swagger_jackson.spring_security_jwt.jwt_authentication.JwtLoginRequest;
import com.Pulson.spring_security_swagger_jackson.spring_security_jwt.jwt_authentication.UserDetailsImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtConstants jwtConstants;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, String authenticationURL, JwtConstants jwtConstants) {
        super.setAuthenticationManager(authenticationManager);
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl(authenticationURL);
        this.jwtConstants = jwtConstants;
    }

    @SneakyThrows
    @Override//Działa przy żądaniu POST na /login
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        JwtLoginRequest loginRequest = new JwtLoginRequest();
        try {
            //Z odebranego JSONa próbujemy stworzyć obiekt JwtLoginRequest(username, password)
            loginRequest = new ObjectMapper().readValue(request.getInputStream(), JwtLoginRequest.class);
            //System.out.println(loginRequest.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Tworzony jest token z loginem i hasłem przekazywany do Authentication Managera, który dokonuje autentykacji
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword(),
                new ArrayList<>()
        );

        //AuthenticationManager, któremu ustawiamy nasz Bean AuthenticationProvider w metodzie configure w SpringSecurityConfig
        //ma dostęp do UserDetailsServiceImpl, czyli klasy wyciągającej UserDetailsImpl po username
        //Stąd AuthenticationManager może sprawdzić, odkodować i przetworzyć podane przez użytkownika login i hasło.
        Authentication authentication = authentication = authenticationManager.authenticate(authenticationToken);
        return authentication; //w tym miejscu w zależności od powodzenia autentykacji automatycznie uruchamiają się metody un/successfulAuthentication
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //Po udanej autentykacji (ustaleniu czy username i hasło się zgadzają), z obiektu Authentication wyciągany jest obiekt userDetails
        //czyli encja wyobrażająca (opakowująca) użytkownika
        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();

        //Tworzony jest JWT Token
        String jwtToken = JWT.create()
                .withSubject(userDetails.getUsername())
                //.withClaim("user_id", userDetails.getId())
                //.withClaim("role", userDetails.getAuthorities().get(0).getAuthority())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtConstants.getExpiration_time()))
                .sign(Algorithm.HMAC512(jwtConstants.getSecret()));

        //do HttpResponse 200 OK dodawany jest header "Authorization" : "Bearer + token"
        response.addHeader(jwtConstants.getHttp_header_string(), jwtConstants.getToken_prefix() + jwtToken);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.sendError(401);
    }

    @Override
    public void setFilterProcessesUrl(String authenticationURL) {
        super.setFilterProcessesUrl(authenticationURL);
    }
}

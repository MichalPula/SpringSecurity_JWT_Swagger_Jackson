package com.Pulson.spring_security_swagger_jackson.spring_security_jwt.jwt_authentication.filters;

import com.Pulson.spring_security_swagger_jackson.spring_security_jwt.jwt_authentication.UserDetailsImpl;
import com.Pulson.spring_security_swagger_jackson.spring_security_jwt.user.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserRepository userRepository;
    private final JwtConstants jwtConstants;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  UserRepository userRepository,
                                  JwtConstants jwtConstants) {
        super(authenticationManager);
        this.userRepository = userRepository;
        this.jwtConstants = jwtConstants;
    }

    @Override
    //Wykonuje się przy każdym requeście, który ma być poddany autoryzacji (sprawdzeniu czy użytkownik może wysłać konkretne zapytanie na endpoint i otrzymać odpowiedź)
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //Wyciągnięcie wartości nagłówka "Authorization", czyli tokenu, który poprzednio nadaliśmy użytkownikowi
        //Teraz za każdym razem User wraca do nas z nadanym mu uprzednio przez nasz serwer nagłówkiem "Authorization" i dołączonym JWT Tokenem
        String token = request.getHeader(jwtConstants.getHttp_header_string());

        //Jeśli token nie jest pusty i zawiera prefix "Bearer " ->
        if (token != null && token.startsWith(jwtConstants.getToken_prefix())) {
            //Dekodujemy token i wyciągamy z niego username
            //Token wcześniej został zakodowany przy użyciu tego samego algorytmu i secretu - dlatego używamy ich ponownie
            try {
                String username = JWT.require(Algorithm.HMAC512(jwtConstants.getSecret()))
                        .build()
                        .verify(token.replace(jwtConstants.getToken_prefix(), ""))
                        .getSubject();
                //Przekazujemy wyszukany w bazie obiekt User do konstruktora UserDetailsImpl
                UserDetailsImpl userDetails = new UserDetailsImpl(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found!")));
                //I zwracamy obiekt zawierający username i authorities naszego użytkownika
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authToken);
            } catch (JWTVerificationException ex) {
                //Jeśli zmieni się jakikolwiek znak w tokenie, rzucony zostanie wyjątek i jako odpowiedź przesłany status 401
                response.setStatus(401);
            }
        }
        chain.doFilter(request, response);
    }
}

package com.massaricompany.eventsapp.events.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.massaricompany.eventsapp.events.model.User;
import com.massaricompany.eventsapp.events.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private ApplicationContext applicationContext;

    private final AuthenticationManager authenticationManager;
    private final String jwtSecret;
    private final int jwtExpirationMs;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, String jwtSecret, int jwtExpirationMs, ApplicationContext applicationContext) {
        this.authenticationManager = authenticationManager;
        this.jwtSecret = jwtSecret;
        this.jwtExpirationMs = jwtExpirationMs;
        this.applicationContext = applicationContext;
    }



    private UserRepository getUserRepository() {
        return applicationContext.getBean(UserRepository.class);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            logger.info("Attempting authentication for user: {}", user.getUsername());

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            logger.error("Error reading request input stream", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String username = ((UserDetails) authResult.getPrincipal()).getUsername();
        User user = getUserRepository().findByUsername(username); // Use the UserRepository to fetch the User object

        // Remaining part of the method

    }


    // ...
}

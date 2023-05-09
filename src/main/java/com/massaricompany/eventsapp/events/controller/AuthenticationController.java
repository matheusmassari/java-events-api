package com.massaricompany.eventsapp.events.controller;

import com.massaricompany.eventsapp.events.dto.userDto.JwtAuthenticationResponse;
import com.massaricompany.eventsapp.events.dto.userDto.LoginRequest;
import com.massaricompany.eventsapp.events.security.JwtTokenProvider;
import com.massaricompany.eventsapp.events.service.user.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserPrincipal userPrincipal = (UserPrincipal) userDetails;

        // Create JwtAuthenticationResponse object
        JwtAuthenticationResponse jwtResponse = new JwtAuthenticationResponse(userPrincipal.getUsername(), userPrincipal.getEmail(), jwt);

        return ResponseEntity.ok(jwtResponse);
    }

}

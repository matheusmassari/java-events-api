package com.massaricompany.eventsapp.events.dto.userDto;


public class JwtAuthenticationResponse {
    private String username;
    private String email;
    private String token;

    public JwtAuthenticationResponse(String username, String email, String token) {
        this.username = username;
        this.email = email;
        this.token = token;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

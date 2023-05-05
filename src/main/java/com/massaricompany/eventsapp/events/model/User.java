package com.massaricompany.eventsapp.events.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "app_user") // Change the table name to "app_user"
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 3, max = 55)
    private String username;

    @NotBlank
    @Size(min = 3, max = 55)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 55)
    private String lastName;
    @NotBlank
    @Size(min = 3, max = 55)
    private String password;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(min = 3, max = 55)
    private String role;

    // Add any other properties you'd like to store for a User

    @OneToMany(mappedBy = "host")
    private Set<Event> hostedEvents = new HashSet<>();

    public User() {
    }

    // Getters and setters for all properties

    // Getters
    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(String role) {
        this.role = role;
    }


}

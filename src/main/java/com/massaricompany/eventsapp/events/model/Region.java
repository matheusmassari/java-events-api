package com.massaricompany.eventsapp.events.model;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "region")
    private Set<Event> events = new HashSet<>();

    public Region() {
    }

    // Getters and setters for all properties
    public void setId(Long id) {
        this.id = id;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    // Setters
    public void setName(String name) {
        this.name = name;
    }


}

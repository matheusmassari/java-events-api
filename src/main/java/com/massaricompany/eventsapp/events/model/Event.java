package com.massaricompany.eventsapp.events.model;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private User host;

    @ManyToMany
    @JoinTable(
            name = "event_cohosts",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "cohost_id")
    )
    private Set<User> coHosts = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    // Add any other properties you'd like to store for an Event

    public Event() {
    }

    // Getters and setters for all properties

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public User getHost() {
        return host;
    }

    public Set<User> getCoHosts() {
        return coHosts;
    }

    public Region getRegion() {
        return region;
    }

    public LocalDateTime getDate() {
        return date;
    }

    // Setters

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public void setCoHosts(Set<User> coHosts) {
        this.coHosts = coHosts;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

}


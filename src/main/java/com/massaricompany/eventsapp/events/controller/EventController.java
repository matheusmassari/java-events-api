package com.massaricompany.eventsapp.events.controller;

import com.massaricompany.eventsapp.events.model.Event;
import com.massaricompany.eventsapp.events.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // Create or Update an Event
    @PostMapping
    public ResponseEntity<Event> save(@RequestBody Event event) {
        Event savedEvent = eventService.save(event);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    // Retrieve an Event by ID
    @GetMapping("/{id}")
    public ResponseEntity<Event> findById(@PathVariable Long id) {
        Event event = eventService.findById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }


    // Retrieve all Events
    @GetMapping
    public ResponseEntity<List<Event>> findAll() {
        List<Event> events = eventService.findAll();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    // Update an Event by ID
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        Event currentEvent = eventService.findById(id);
        currentEvent.setName(eventDetails.getName());
        currentEvent.setDescription(eventDetails.getDescription());
        currentEvent.setDate(eventDetails.getDate());
        currentEvent.setHost(eventDetails.getHost());
        currentEvent.setCoHosts(eventDetails.getCoHosts());
        currentEvent.setRegion(eventDetails.getRegion());
        Event updatedEvent = eventService.save(currentEvent);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }


    // Delete an Event by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        eventService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

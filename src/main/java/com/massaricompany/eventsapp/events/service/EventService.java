package com.massaricompany.eventsapp.events.service;

import com.massaricompany.eventsapp.events.exception.ResourceNotFoundException;
import com.massaricompany.eventsapp.events.model.Event;
import com.massaricompany.eventsapp.events.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // Create or Update an Event
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    // Retrieve an Event by ID
    public Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
    }


    // Retrieve all Events
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    // Delete an Event by ID
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }
}

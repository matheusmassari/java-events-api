package com.massaricompany.eventsapp.events.repository;

import com.massaricompany.eventsapp.events.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
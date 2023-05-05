package com.massaricompany.eventsapp.events.repository;


import com.massaricompany.eventsapp.events.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
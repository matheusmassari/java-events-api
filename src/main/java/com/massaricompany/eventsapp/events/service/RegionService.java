package com.massaricompany.eventsapp.events.service;

import com.massaricompany.eventsapp.events.exception.ResourceNotFoundException;
import com.massaricompany.eventsapp.events.model.Region;
import com.massaricompany.eventsapp.events.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {
    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    // Create or Update a Region
    public Region save(Region region) {
        return regionRepository.save(region);
    }

    // Retrieve a Region by ID
    public Region findById(Long id) {
        return regionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Region not found with id: " + id));
    }


    // Retrieve all Regions
    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    // Delete a Region by ID
    public void deleteById(Long id) {
        regionRepository.deleteById(id);
    }
}

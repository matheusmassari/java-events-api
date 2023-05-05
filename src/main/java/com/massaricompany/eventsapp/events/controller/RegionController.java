package com.massaricompany.eventsapp.events.controller;

import com.massaricompany.eventsapp.events.model.Region;
import com.massaricompany.eventsapp.events.service.RegionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
public class RegionController {
    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    // Create or Update a Region
    @PostMapping
    public ResponseEntity<Region> save(@RequestBody Region region) {
        Region savedRegion = regionService.save(region);
        return new ResponseEntity<>(savedRegion, HttpStatus.CREATED);
    }

    // Retrieve a Region by ID
    @GetMapping("/{id}")
    public ResponseEntity<Region> findById(@PathVariable Long id) {
        Region region = regionService.findById(id);
        return new ResponseEntity<>(region, HttpStatus.OK);
    }


    // Retrieve all Regions
    @GetMapping
    public ResponseEntity<List<Region>> findAll() {
        List<Region> regions = regionService.findAll();
        return new ResponseEntity<>(regions, HttpStatus.OK);
    }

    // Update a Region by ID
    @PutMapping("/{id}")
    public ResponseEntity<Region> updateRegion(@PathVariable Long id, @RequestBody Region regionDetails) {
        Region currentRegion = regionService.findById(id);
        currentRegion.setName(regionDetails.getName());
        Region updatedRegion = regionService.save(currentRegion);
        return new ResponseEntity<>(updatedRegion, HttpStatus.OK);
    }


    // Delete a Region by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        regionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

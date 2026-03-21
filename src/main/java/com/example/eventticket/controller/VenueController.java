package com.example.eventticket.controller;

import com.example.eventticket.entity.Venue;
import com.example.eventticket.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/venues")
public class VenueController {
    private final VenueService venueService;

    // Class Constructor
    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    // POST /api/venues
    @PostMapping
    public ResponseEntity<Venue> createVenue(@RequestBody Venue venue) {
        Venue savedVenue = venueService.createVenue(venue);
        return new ResponseEntity<>(savedVenue, HttpStatus.CREATED);
    }
}
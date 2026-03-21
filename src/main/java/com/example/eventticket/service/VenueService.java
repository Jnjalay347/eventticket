package com.example.eventticket.service;

import com.example.eventticket.entity.Venue;
import com.example.eventticket.repository.VenueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VenueService {
    private final VenueRepository venueRepository;

    // Class Constructor
    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Transactional
    public Venue createVenue(Venue venue) {
        return venueRepository.save(venue);
    }
}
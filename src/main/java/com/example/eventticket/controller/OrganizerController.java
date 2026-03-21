package com.example.eventticket.controller;

import com.example.eventticket.entity.Organizer;
import com.example.eventticket.service.OrganizerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizers")
public class OrganizerController {
    private final OrganizerService organizerService;

    // Class Constructor
    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    // POST /api/organizers
    @PostMapping
    public ResponseEntity<Organizer> createOrganizer(@RequestBody Organizer organizer) {
        Organizer savedOrganizer = organizerService.createOrganizer(organizer);
        return new ResponseEntity<>(savedOrganizer, HttpStatus.CREATED);
    }
}
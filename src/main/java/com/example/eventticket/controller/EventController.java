package com.example.eventticket.controller;

import com.example.eventticket.dto.EventRequestDTO;
import com.example.eventticket.dto.EventResponseDTO;
import com.example.eventticket.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    // Class Constructor
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // POST /api/events
    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(@RequestBody EventRequestDTO request) {
        EventResponseDTO savedEvent = eventService.createEvent(request);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    // GET /api/events
    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getUpcomingEvents() {
        return ResponseEntity.ok(eventService.getUpcomingEvents());
    }

    // GET /api/events/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }
}
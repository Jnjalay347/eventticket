//package com.example.eventticket.controller;
//
//import com.example.eventticket.dto.EventResponseDTO;
//import com.example.eventticket.entity.Event;
//import com.example.eventticket.service.EventService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/events")
//public class EventController {
//
//    private final EventService eventService;
//
//    public EventController(EventService eventService) {
//        this.eventService = eventService;
//    }
//
//    @PostMapping
//    public ResponseEntity<EventResponseDTO> createEvent(@RequestBody Event event) {
//        EventResponseDTO savedEvent = eventService.createEvent(event);
//        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<EventResponseDTO>> getUpcomingEvents() {
//        return ResponseEntity.ok(eventService.getUpcomingEvents());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long id) {
//        return ResponseEntity.ok(eventService.getEventById(id));
//    }
//}


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

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(@RequestBody EventRequestDTO request) {
        EventResponseDTO savedEvent = eventService.createEvent(request);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getUpcomingEvents() {
        return ResponseEntity.ok(eventService.getUpcomingEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }
}
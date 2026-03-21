package com.example.eventticket.controller;

import com.example.eventticket.dto.AttendeeBookingsDTO;
import com.example.eventticket.entity.Attendee;
import com.example.eventticket.service.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendees")
public class AttendeeController {
    private final AttendeeService attendeeService;

    // Class Constructor
    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    // POST /api/attendees
    @PostMapping
    public ResponseEntity<Attendee> createAttendee(@RequestBody Attendee attendee) {
        Attendee savedAttendee = attendeeService.createAttendee(attendee);
        return new ResponseEntity<>(savedAttendee, HttpStatus.CREATED);
    }

    // GET /api/attendees/{id}/bookings
    @GetMapping("/{id}/bookings")
    public ResponseEntity<AttendeeBookingsDTO> getBookingsForAttendee(@PathVariable Long id) {
        return ResponseEntity.ok(attendeeService.getBookingsForAttendee(id));
    }
}
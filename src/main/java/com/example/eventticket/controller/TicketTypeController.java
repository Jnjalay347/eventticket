package com.example.eventticket.controller;

import com.example.eventticket.entity.TicketType;
import com.example.eventticket.service.TicketTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticket-types")
public class TicketTypeController {

    private final TicketTypeService ticketTypeService;

    public TicketTypeController(TicketTypeService ticketTypeService) {
        this.ticketTypeService = ticketTypeService;
    }

    @PostMapping("/event/{eventId}")
    public ResponseEntity<TicketType> createTicketType(@PathVariable Long eventId,
                                                       @RequestBody TicketType ticketType) {
        TicketType saved = ticketTypeService.createTicketType(eventId, ticketType);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
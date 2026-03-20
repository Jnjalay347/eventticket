package com.example.eventticket.controller;

import com.example.eventticket.dto.RevenueDTO;
import com.example.eventticket.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class RevenueController {

    private final EventService eventService;

    public RevenueController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{id}/revenue")
    public ResponseEntity<RevenueDTO> getRevenue(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getRevenueForEvent(id));
    }
}
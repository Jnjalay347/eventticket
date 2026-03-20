package com.example.eventticket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventRequestDTO {
    private String title;
    private String description;
    private LocalDateTime eventDate;
    private String status;
    private Long organizerId;
    private Long venueId;
}
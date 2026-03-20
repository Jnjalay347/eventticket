package com.example.eventticket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendeeBookingsDTO {
    private String attendeeName;
    private List<BookingResponseDTO> bookings;
}
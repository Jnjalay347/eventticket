package com.example.eventticket.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO {
    private String bookingReference;
    private LocalDateTime bookingDate;
    private String status;
    private String attendeeName;
    private String eventTitle;
    private String ticketTypeName;
    private Double price;
}
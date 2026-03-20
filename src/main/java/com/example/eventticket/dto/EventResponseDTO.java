package com.example.eventticket.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDTO {

    private String title;
    private String description;
    private List<TicketTypesDTO> ticketTypes;
    private String organizerName;
    private String venueName;
}
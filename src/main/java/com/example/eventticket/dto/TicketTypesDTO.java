package com.example.eventticket.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketTypesDTO {

    private String name;
    private Double price;
    private Integer quantityAvailable;
}
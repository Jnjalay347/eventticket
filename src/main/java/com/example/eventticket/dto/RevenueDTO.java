package com.example.eventticket.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RevenueDTO {
    private String eventTitle;
    private Double totalRevenue;
}
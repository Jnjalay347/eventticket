package com.example.eventticket.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketTypeId;

    private String name;

    private Double price;

    private Integer quantityAvailable;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnoreProperties({"ticketTypes"})
    private Event event;

    @JsonIgnore
    @OneToMany(mappedBy = "ticketType")
    private List<Booking> bookings;
}
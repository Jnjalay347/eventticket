package com.example.eventticket.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendeeId;

    private String name;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "attendee")
    private List<Booking> bookings;
}
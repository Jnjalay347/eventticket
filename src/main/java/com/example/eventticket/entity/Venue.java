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
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long venueId;

    private String name;

    private String address;

    private String city;

    private Integer totalCapacity;

    @JsonIgnore
    @OneToMany(mappedBy = "venue")
    private List<Event> events;
}
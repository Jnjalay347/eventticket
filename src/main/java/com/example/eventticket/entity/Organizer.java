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
public class Organizer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organizerId;

    private String name;

    @Column(unique = true)
    private String email;

    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "organizer")
    private List<Event> events;
}
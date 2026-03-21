package com.example.eventticket.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    private String title;

    private String description;

    private LocalDateTime eventDate;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    public enum EventStatus {
        UPCOMING,
        ONGOING,
        CANCELLED,
        COMPLETED
    }

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    @JsonIgnoreProperties({"events"})
    private Organizer organizer;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    @JsonIgnoreProperties({"events"})
    private Venue venue;

    @JsonIgnore
    @OneToMany(mappedBy = "event")
    private List<TicketType> ticketTypes;
}
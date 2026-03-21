//package com.example.eventticket.service;
//
//import com.example.eventticket.dto.EventResponseDTO;
//import com.example.eventticket.dto.RevenueDTO;
//import com.example.eventticket.dto.TicketTypesDTO;
//import com.example.eventticket.entity.Event;
//import com.example.eventticket.entity.Organizer;
//import com.example.eventticket.entity.Venue;
//import com.example.eventticket.repository.BookingRepository;
//import com.example.eventticket.repository.EventRepository;
//import com.example.eventticket.repository.OrganizerRepository;
//import com.example.eventticket.repository.VenueRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static com.example.eventticket.entity.Event.EventStatus.UPCOMING;
//
//@Service
//public class EventService {
//
//    private final EventRepository eventRepository;
//    private final OrganizerRepository organizerRepository;
//    private final VenueRepository venueRepository;
//    private final BookingRepository bookingRepository;
//
//    public EventService(EventRepository eventRepository,
//                        OrganizerRepository organizerRepository,
//                        VenueRepository venueRepository,
//                        BookingRepository bookingRepository) {
//        this.eventRepository = eventRepository;
//        this.organizerRepository = organizerRepository;
//        this.venueRepository = venueRepository;
//        this.bookingRepository = bookingRepository;
//    }
//
//    @Transactional
//    public EventResponseDTO createEvent(Event event) {
//        if (event.getOrganizer() == null || event.getOrganizer().getOrganizerId() == null) {
//            throw new RuntimeException("Organizer ID is required.");
//        }
//
//        if (event.getVenue() == null || event.getVenue().getVenueId() == null) {
//            throw new RuntimeException("Venue ID is required.");
//        }
//
//        Organizer organizer = organizerRepository.findById(event.getOrganizer().getOrganizerId())
//                .orElseThrow(() -> new RuntimeException("Organizer not found."));
//
//        Venue venue = venueRepository.findById(event.getVenue().getVenueId())
//                .orElseThrow(() -> new RuntimeException("Venue not found."));
//
//        event.setOrganizer(organizer);
//        event.setVenue(venue);
//
//        Event savedEvent = eventRepository.save(event);
//        return mapToEventResponseDTO(savedEvent);
//    }
//
//    public List<EventResponseDTO> getUpcomingEvents() {
//        return eventRepository.findByStatus(UPCOMING)
//                .stream()
//                .map(this::mapToEventResponseDTO)
//                .toList();
//    }
//
//    public EventResponseDTO getEventById(Long id) {
//        Event event = eventRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Event not found."));
//        return mapToEventResponseDTO(event);
//    }
//
//    public RevenueDTO getRevenueForEvent(Long eventId) {
//        Event event = eventRepository.findById(eventId)
//                .orElseThrow(() -> new RuntimeException("Event not found."));
//
//        Double revenue = bookingRepository.calculateRevenue(eventId);
//        if (revenue == null) {
//            revenue = 0.0;
//        }
//
//        return new RevenueDTO(event.getTitle(), revenue);
//    }
//
//    private EventResponseDTO mapToEventResponseDTO(Event event) {
//        List<TicketTypesDTO> ticketTypeDTOs = event.getTicketTypes() == null
//                ? List.of()
//                : event.getTicketTypes().stream()
//                .map(ticket -> new TicketTypesDTO(
//                        ticket.getName(),
//                        ticket.getPrice(),
//                        ticket.getQuantityAvailable()
//                ))
//                .toList();
//
//        return new EventResponseDTO(
//                event.getTitle(),
//                event.getDescription(),
//                ticketTypeDTOs,
//                event.getOrganizer().getName(),
//                event.getVenue().getName()
//        );
//    }
//}

package com.example.eventticket.service;

import com.example.eventticket.dto.EventRequestDTO;
import com.example.eventticket.dto.EventResponseDTO;
import com.example.eventticket.dto.RevenueDTO;
import com.example.eventticket.dto.TicketTypesDTO;
import com.example.eventticket.entity.Event;
import com.example.eventticket.entity.Organizer;
import com.example.eventticket.entity.Venue;
import com.example.eventticket.repository.BookingRepository;
import com.example.eventticket.repository.EventRepository;
import com.example.eventticket.repository.OrganizerRepository;
import com.example.eventticket.repository.VenueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final OrganizerRepository organizerRepository;
    private final VenueRepository venueRepository;
    private final BookingRepository bookingRepository;

    // Class Constructor
    public EventService(EventRepository eventRepository,
                        OrganizerRepository organizerRepository,
                        VenueRepository venueRepository,
                        BookingRepository bookingRepository) {
        this.eventRepository = eventRepository;
        this.organizerRepository = organizerRepository;
        this.venueRepository = venueRepository;
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public EventResponseDTO createEvent(EventRequestDTO request) {
        // Retrieves organizer
        Organizer organizer = organizerRepository.findById(request.getOrganizerId())
                .orElseThrow(() -> new RuntimeException("Organizer not found."));

        // Retrieves venue
        Venue venue = venueRepository.findById(request.getVenueId())
                .orElseThrow(() -> new RuntimeException("Venue not found."));

        // Creates new event
        Event event = new Event();
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setEventDate(request.getEventDate());
        event.setStatus(Event.EventStatus.valueOf(request.getStatus()));
        event.setOrganizer(organizer);
        event.setVenue(venue);

        // Saves event information
        Event savedEvent = eventRepository.save(event);
        return mapToEventResponseDTO(savedEvent);
    }

    public List<EventResponseDTO> getUpcomingEvents() {
        return eventRepository.findByStatus(Event.EventStatus.UPCOMING)
                .stream()
                .map(this::mapToEventResponseDTO)
                .toList();
    }

    public EventResponseDTO getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found."));
        return mapToEventResponseDTO(event);
    }

    public RevenueDTO getRevenueForEvent(Long eventId) {
        // Retrieves event
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found."));

        // Calculate total revenue from bookings for event
        Double revenue = bookingRepository.calculateRevenue(eventId);
        if (revenue == null) {
            revenue = 0.0;
        }

        // Returns: Event title + total confirmed revenue
        return new RevenueDTO(event.getTitle(), revenue);
    }

    private EventResponseDTO mapToEventResponseDTO(Event event) {
        // Converts ticket types to a list of TicketTypesDTO
        List<TicketTypesDTO> ticketTypeDTOs = event.getTicketTypes() == null
                ? List.of()
                : event.getTicketTypes().stream()
                .map(ticket -> new TicketTypesDTO(
                        ticket.getName(),
                        ticket.getPrice(),
                        ticket.getQuantityAvailable()
                ))
                .toList();

        // Returns: Event details + list of TicketTypeDTO + organizer name + venue name
        return new EventResponseDTO(
                event.getTitle(),
                event.getDescription(),
                ticketTypeDTOs,
                event.getOrganizer().getName(),
                event.getVenue().getName()
        );
    }
}
package com.example.eventticket.service;

import com.example.eventticket.entity.Event;
import com.example.eventticket.entity.TicketType;
import com.example.eventticket.repository.EventRepository;
import com.example.eventticket.repository.TicketTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketTypeService {

    private final TicketTypeRepository ticketTypeRepository;
    private final EventRepository eventRepository;

    public TicketTypeService(TicketTypeRepository ticketTypeRepository,
                             EventRepository eventRepository) {
        this.ticketTypeRepository = ticketTypeRepository;
        this.eventRepository = eventRepository;
    }

    @Transactional
    public TicketType createTicketType(Long eventId, TicketType ticketType) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found."));

        if (ticketType.getPrice() == null || ticketType.getPrice() < 0) {
            throw new RuntimeException("Ticket price must be greater than or equal to 0.");
        }

        ticketType.setEvent(event);
        return ticketTypeRepository.save(ticketType);
    }
}
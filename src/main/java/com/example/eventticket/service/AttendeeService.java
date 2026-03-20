package com.example.eventticket.service;

import com.example.eventticket.dto.AttendeeBookingsDTO;
import com.example.eventticket.dto.BookingResponseDTO;
import com.example.eventticket.entity.Attendee;
import com.example.eventticket.entity.Booking;
import com.example.eventticket.repository.AttendeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;

    public AttendeeService(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    @Transactional
    public Attendee createAttendee(Attendee attendee) {
        if (attendeeRepository.existsByEmail(attendee.getEmail())) {
            throw new RuntimeException("Attendee email already exists.");
        }
        return attendeeRepository.save(attendee);
    }

    public AttendeeBookingsDTO getBookingsForAttendee(Long attendeeId) {
        Attendee attendee = attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new RuntimeException("Attendee not found."));

        List<BookingResponseDTO> bookingDTOs = attendee.getBookings() == null
                ? List.of()
                : attendee.getBookings().stream()
                .map(this::mapToBookingResponseDTO)
                .toList();

        return new AttendeeBookingsDTO(attendee.getName(), bookingDTOs);
    }

    private BookingResponseDTO mapToBookingResponseDTO(Booking booking) {
        return new BookingResponseDTO(
                booking.getBookingReference(),
                booking.getBookingDate(),
                booking.getPaymentStatus().name(),
                booking.getAttendee().getName(),
                booking.getTicketType().getEvent().getTitle(),
                booking.getTicketType().getName(),
                booking.getTicketType().getPrice()
        );
    }
}
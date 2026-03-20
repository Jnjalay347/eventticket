package com.example.eventticket.service;

import com.example.eventticket.dto.BookingResponseDTO;
import com.example.eventticket.entity.Attendee;
import com.example.eventticket.entity.Booking;
import com.example.eventticket.entity.TicketType;
import com.example.eventticket.repository.AttendeeRepository;
import com.example.eventticket.repository.BookingRepository;
import com.example.eventticket.repository.TicketTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.example.eventticket.entity.Booking.PaymentStatus.CANCELLED;
import static com.example.eventticket.entity.Booking.PaymentStatus.CONFIRMED;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final AttendeeRepository attendeeRepository;
    private final TicketTypeRepository ticketTypeRepository;

    public BookingService(BookingRepository bookingRepository,
                          AttendeeRepository attendeeRepository,
                          TicketTypeRepository ticketTypeRepository) {
        this.bookingRepository = bookingRepository;
        this.attendeeRepository = attendeeRepository;
        this.ticketTypeRepository = ticketTypeRepository;
    }

    @Transactional
    public BookingResponseDTO createBooking(Long attendeeId, Long ticketTypeId) {
        Attendee attendee = attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new RuntimeException("Attendee not found."));

        TicketType ticketType = ticketTypeRepository.findById(ticketTypeId)
                .orElseThrow(() -> new RuntimeException("Ticket type not found."));

        if (ticketType.getQuantityAvailable() == null || ticketType.getQuantityAvailable() <= 0) {
            throw new RuntimeException("Sorry, this ticket type is sold out.");
        }

        boolean alreadyBooked = bookingRepository
                .existsByAttendee_AttendeeIdAndTicketType_TicketTypeId(attendeeId, ticketTypeId);

        if (alreadyBooked) {
            throw new RuntimeException("You have already booked this ticket type.");
        }

        ticketType.setQuantityAvailable(ticketType.getQuantityAvailable() - 1);
        ticketTypeRepository.save(ticketType);

        Booking booking = new Booking();
        booking.setAttendee(attendee);
        booking.setTicketType(ticketType);
        booking.setBookingDate(LocalDateTime.now());
        booking.setPaymentStatus(CONFIRMED);

        Booking savedBooking = bookingRepository.save(booking);

        String bookingReference = String.format(
                "TKT-%d-%05d",
                LocalDateTime.now().getYear(),
                savedBooking.getBookingId()
        );

        savedBooking.setBookingReference(bookingReference);
        Booking updatedBooking = bookingRepository.save(savedBooking);

        return mapToBookingResponseDTO(updatedBooking);
    }

    @Transactional
    public BookingResponseDTO cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found."));

        if (booking.getPaymentStatus() == CANCELLED) {
            throw new RuntimeException("Booking is already cancelled.");
        }

        booking.setPaymentStatus(CANCELLED);

        TicketType ticketType = booking.getTicketType();
        ticketType.setQuantityAvailable(ticketType.getQuantityAvailable() + 1);

        ticketTypeRepository.save(ticketType);
        Booking updatedBooking = bookingRepository.save(booking);

        return mapToBookingResponseDTO(updatedBooking);
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
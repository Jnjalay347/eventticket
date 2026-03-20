package com.example.eventticket.repository;

import com.example.eventticket.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    boolean existsByAttendee_AttendeeIdAndTicketType_TicketTypeId(Long attendeeId, Long ticketTypeId);

    @Query("""
           SELECT SUM(t.price)
           FROM Booking b
           JOIN b.ticketType t
           WHERE t.event.eventId = :eventId
           AND b.paymentStatus = 'CONFIRMED'
           """)
    Double calculateRevenue(Long eventId);
}
# Event Ticketing System

Project works.
Here are the testing queries for Postman.

### POST /api/organizers
http://localhost:8080/api/organizers
{
"name": "Live Nation",
"email": "contact@livenation.com",
"phone": "555-111-2222"
}

### POST /api/venues
http://localhost:8080/api/venues
{
"name": "Crypto Arena",
"address": "111 S Figueroa St",
"city": "Los Angeles",
"totalCapacity": 20000
}

### POST /api/events
http://localhost:8080/api/events
{
"title": "Spring Music Fest",
"description": "Annual live music festival",
"eventDate": "2026-04-15T19:30:00",
"status": "UPCOMING",
"organizerId": 1,
"venueId": 1
}

### POST /api/ticket-types/event/1
http://localhost:8080/api/ticket-types/event/1
{
"name": "VIP",
"price": 150.0,
"quantityAvailable": 50
}

### POST /api/attendees
http://localhost:8080/api/attendees
{
"name": "John Wick",
"email": "johnwick@email.com"
}

### POST /api/bookings
http://localhost:8080/api/bookings
{
"attendeeId": 1,
"ticketTypeId": 1
}

### GET event by id
http://localhost:8080/api/events/1

### GET attendee bookings
http://localhost:8080/api/attendees/1/bookings

### GET event revenue
http://localhost:8080/api/events/1/revenue

### PUT /api/bookings/1/cancel
http://localhost:8080/api/bookings/1/cancel
No body needed.
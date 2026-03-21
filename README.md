# Event Ticketing System
### Team Members
- Toan Tran
- James Nguyen
- Moses Bui
- An Nguyen

<u>Below are the API requests needed for Postman testing:</u>
- Note 1: If inspecting the raw README.md file, simply remove any backslashes (\\).
- Note 2: Ensure that IntelliJ, Postman, and PostgreSQL (pgAdmin4) are all configured correctly & running at the same time.
- Note 3: Follow these API requests in exact order to demo the full experience.

### 1) POST /api/organizers
- Scenario: Live Nation organizer registers
- http://localhost:8080/api/organizers
- {\
"name": "Live Nation",\
"email": "contact@livenation.com",\
"phone": "800-653-8000"\
}

### 2) POST /api/venues
- Scenario: Staples Center venue registers
- http://localhost:8080/api/venues
- {\
"name": "Staples Center",\
"address": "1111 S Figueroa St",\
"city": "Los Angeles",\
"totalCapacity": 20000\
}

### 3) POST /api/venues
- Scenario: Empire Polo Club venue registers
- http://localhost:8080/api/venues
- {\
  "name": "Empire Polo Club",\
  "address": "81800 Avenue 51",\
  "city": "Indio",\
  "totalCapacity": 125000\
}

### 4) POST /api/events
- Scenario: Spring Music Fest event registers
- http://localhost:8080/api/events
- {\
"title": "Spring Music Fest",\
"description": "Annual live music festival",\
"eventDate": "2027-03-31T19:30:00",\
"status": "UPCOMING",\
"organizerId": 1,\
"venueId": 1\
}

### 5) POST /api/events
- Scenario: Coachella event registers
- http://localhost:8080/api/events
- {\
  "title": "Coachella",\
  "description": "Annual live music festival",\
  "eventDate": "2027-04-15T15:00:00",\
  "status": "UPCOMING",\
  "organizerId": 1,\
  "venueId": 2\
  }

### 6) GET /api/events
- Scenario: Check all available upcoming events
- http://localhost:8080/api/events
- No body needed.

### 7) GET event by id
- Scenario: Check Spring Music Fest event
- http://localhost:8080/api/events/1
- No body needed.

### 8) POST /api/ticket-types/event/1
- Note: Add a **VIP** ticket tier, for Spring Music Fest
- http://localhost:8080/api/ticket-types/event/1
- {\
  "name": "VIP",\
  "price": 150.0,\
  "quantityAvailable": 50\
  }

### 9) POST /api/ticket-types/event/1
- Note: Adds a **General Admission** ticket tier, for Spring Music Fest
- http://localhost:8080/api/ticket-types/event/1
- {\
  "name": "General Admission",\
  "price": 75.0,\
  "quantityAvailable": 100\
  }

### 10) POST /api/attendees
- Scenario: John Wick signs up
- http://localhost:8080/api/attendees
- {\
  "name": "John Wick",\
  "email": "johnwick@gmail.com"\
  }

### 11) POST /api/bookings
- Scenario: John Wick books a VIP ticket to Spring Music Fest
- http://localhost:8080/api/bookings
- {\
  "attendeeId": 1,\
  "ticketTypeId": 1\
  }

### 12) GET /api/attendees/{1}/bookings
- Scenario: John Wick checks his booking information
- http://localhost:8080/api/attendees/1/bookings
- No body needed.

### 13) GET /api/events/{1}/revenue
- Scenario: Check ticket revenue made, from Spring Music Fest
- http://localhost:8080/api/events/1/revenue
- No body needed.

### 14) PUT /api/bookings/1/cancel
- Scenario: John Wick decides to cancel his VIP ticket
- http://localhost:8080/api/bookings/1/cancel
- No body needed.

### 15) GET /api/attendees/{1}/bookings
- Scenario: John Wick confirms he canceled his VIP ticket booking
- http://localhost:8080/api/attendees/1/bookings
- No body needed.
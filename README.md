# Event Ticketing System
### Team Members
- Toan Tran
- James Nguyen
- Moses Bui
- An Nguyen

<u>Below are the API requests needed for Postman testing:</u>

### 1) POST /api/organizers
- http://localhost:8080/api/organizers
- {\
"name": "Live Nation",\
"email": "contact@livenation.com",\
"phone": "800-653-8000"\
}

### 2) POST /api/venues
- http://localhost:8080/api/venues
- {\
"name": "Staples Center",\
"address": "1111 S Figueroa St",\
"city": "Los Angeles",\
"totalCapacity": 20000\
}

### 3) POST /api/events
- http://localhost:8080/api/events
- {\
"title": "Spring Music Fest",\
"description": "Annual live music festival",\
"eventDate": "2026-04-15T19:30:00",\
"status": "UPCOMING",\
"organizerId": 1,\
"venueId": 1\
}

### 4) GET /api/events
- http://localhost:8080/api/events
- No body needed.

### 5) GET event by id
- http://localhost:8080/api/events/1
- No body needed.

### 6) POST /api/attendees
- http://localhost:8080/api/attendees
- {\
  "name": "John Wick",\
  "email": "johnwick@gmail.com"\
  }

### 7) POST /api/bookings
- http://localhost:8080/api/bookings
- {\
  "attendeeId": 1,\
  "ticketTypeId": 1\
  }

### 8) PUT /api/bookings/1/cancel
- http://localhost:8080/api/bookings/1/cancel
- No body needed.

### 9) GET /api/events/{1}/revenue
- http://localhost:8080/api/events/1/revenue
- No body needed.

### 10) GET /api/attendees/{1}/bookings
- http://localhost:8080/api/attendees/1/bookings
- No body needed.

### NOTE
- If inspecting the raw .md file, simply remove any backslashes (\\).
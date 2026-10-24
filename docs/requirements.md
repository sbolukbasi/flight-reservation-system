# Flight Reservation System

## 1. Project Goal
A REST API for searching flights and managing flight seat
reservations, built with Java and Spring Boot.

## 2. User Roles
- Visitor: Search for flights and view flight details.
- User: Register, log in, view available seats, create
  bookings, view their own bookings, and cancel them.
- Admin: Manage airports and flights.
- The 'user' and 'admin' roles are stored in the users table.
  'visitors' are unauthenticated users and do not require a
  record in the database.

## 3. Business Rules
1. A user can have multiple bookings.
2. Each flight has one departure airport and one arrival airport.
3. The departure and arrival airports must be different.
4. Arrival time must be later than departure time.
5. A seat cannot have more than one active booking for the same flight.
6. Users can cancel only their own bookings.
7. Cancelled bookings remain in the database as history.

## 4. Scope
- Java and Spring Boot REST API
- PostgreSQL database
- Layered architecture
- Authentication and role-based authorization
- Database migrations
- Automated tests
- Docker-based local environment

## 5. Out of Scope
- Real payment processing
- Frontend application
- Microservices

  These features may be considered in future versions.
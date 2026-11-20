# Flight Reservation System
A backend-focused REST API for searching flights and managing flight seat reservations, built with Java and Spring Boot.

## Project Overview
The goal of this project is to build a flight reservation backend using a relational database, layered architecture, and REST API design principles.

## Technology Stack
- Language: Java
- Framework: Spring Boot
- Data Access: Spring Data JPA
- Build Tool: Maven
- Database: PostgreSQL

## Architecture
The project follows a layered architecture:
- Entity Layer: Represents the database entities and their relationships.
- Repository Layer: Handles database access using Spring Data JPA.
- Service Layer: Contains business logic and application operations.
- DTO Layer: Handles request data and input validation.
- Exception Layer: Defines custom exceptions for application errors.

## Database Design
- The DBML source file is available at [docs/database.dbml](docs/database.dbml)
- Database Schema Diagram: [docs/images/erd.png](docs/images/erd.png)
- SQL schema: [database/schema.sql](database/schema.sql)

## Implemented Features
- PostgreSQL database schema for users, passengers, airports, flights, flight seats, and bookings, with relational constraints.
- JPA entity mappings and Spring Data JPA repositories.
- Flight search by departure airport, arrival airport, and departure date using a custom JPQL query.
- Flight retrieval by ID, creation, and update operations in the service layer.
- Separate request DTOs for flight creation and update, with Jakarta Bean Validation.
- Custom runtime exceptions for business rule violations and missing resources.

## Project Status
Currently in development.

The database design, entity mappings, repository layer, and initial flight management operations have been implemented. Development is currently focused on completing the service layer before moving on to REST API controllers, authentication, and testing.
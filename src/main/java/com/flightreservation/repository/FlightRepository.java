package com.flightreservation.repository;

import com.flightreservation.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    // Finds flights for a specific route within the given departure time range.
    // The start time is inclusive, while the end time is exclusive.
    @Query("""
            SELECT f FROM Flight f
            WHERE f.departureAirport.iataCode = :departureIataCode
                AND f.arrivalAirport.iataCode = :arrivalIataCode
                AND f.departureTime >= :startDateTime
                AND f.departureTime < :endDateTime
    """)
    List<Flight> findByRouteAndDepartureTime(
            @Param("departureIataCode") String departureIataCode,
            @Param("arrivalIataCode") String arrivalIataCode,
            @Param("startDateTime") OffsetDateTime startDateTime,
            @Param("endDateTime") OffsetDateTime endDateTime
            );

    Optional<Flight> findById(Long id);
}

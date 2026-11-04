package com.flightreservation.repository;

import com.flightreservation.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    Optional<Airport> findByIataCode(String iataCode);

    boolean existsByIataCode(String iataCode);
}

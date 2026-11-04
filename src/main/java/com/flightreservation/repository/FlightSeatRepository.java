package com.flightreservation.repository;

import com.flightreservation.entity.FlightSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightSeatRepository extends JpaRepository<FlightSeat, Long> {

    // Lists the seats for a specific flight
    List<FlightSeat> findByFlight_Id(Long id);

    // Checks whether a specific seat number is existing on a given flight
    boolean existsByFlight_IdAndSeatNumber(Long flightId, String seatNumber);
}
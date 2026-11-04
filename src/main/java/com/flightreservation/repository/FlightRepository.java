package com.flightreservation.repository;

import com.flightreservation.entity.Airport;
import com.flightreservation.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByDepartureAirport_IataCodeAndArrivalAirport_IataCode(String departureIataCode, String arrivalIataCode);

}

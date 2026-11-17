package com.flightreservation.service;

import com.flightreservation.dto.request.FlightCreateRequest;
import com.flightreservation.dto.request.FlightUpdateRequest;
import com.flightreservation.entity.Airport;
import com.flightreservation.entity.Flight;
import com.flightreservation.exception.BusinessException;
import com.flightreservation.exception.ResourceNotFoundException;
import com.flightreservation.repository.AirportRepository;
import com.flightreservation.repository.FlightRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class FlightService {

    // Defines the time zone used to interpret departure dates during flight searches.
    private static final ZoneId FLIGHT_SEARCH_ZONE = ZoneId.of("Europe/Istanbul");

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    public FlightService (FlightRepository flightRepository, AirportRepository airportRepository){
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    // Searches for flights based on departure airport, arrival airport, and departure date.
    @Transactional(readOnly = true)
    public List<Flight> searchFlights(String departureIataCode, String arrivalIataCode, LocalDate departureDate){

        // Calculate next day
        LocalDate nextDate = departureDate.plusDays(1);

        // Convert the selected departure date to the start of the day (00:00) in Istanbul time.
        OffsetDateTime startDateTime = departureDate.atStartOfDay(FLIGHT_SEARCH_ZONE).toOffsetDateTime();

        // Convert the next day to the end boundary of the search range (exclusive).
        OffsetDateTime endDateTime = nextDate.atStartOfDay(FLIGHT_SEARCH_ZONE).toOffsetDateTime();

        // Search flights using the FlightRepository.
        return flightRepository.findByRouteAndDepartureTime(departureIataCode, arrivalIataCode, startDateTime, endDateTime);
    }

    // Retrieves a flight by its ID.
    // Throws ResourceNotFoundException if the flight does not exist.
    @Transactional(readOnly = true)
    public Flight getFlightById(Long flightId){
        return flightRepository.findById(flightId).orElseThrow( () -> new ResourceNotFoundException("Flight not found with id: " + flightId));
    }

    // Creates a new flight using the provided flight information.
    @Transactional
    public Flight createFlight(FlightCreateRequest request){

        // Retrieve the Airport entity associated with the departure IATA code.
        Airport departureAirport = airportRepository.findByIataCode(request.getDepartureIataCode()).orElseThrow(
                ()-> new ResourceNotFoundException("Departure Airport not found with IATA code: " + request.getDepartureIataCode())
        );

        // Retrieve the Airport entity associated with the arrival IATA code.
        Airport arrivalAirport = airportRepository.findByIataCode(request.getArrivalIataCode()).orElseThrow(
                ()-> new ResourceNotFoundException("Arrival airport not found with IATA code: " + request.getArrivalIataCode())
        );

        // Check whether departure and arrival airports are different.
        if (departureAirport.getId().equals(arrivalAirport.getId())){
            throw new BusinessException("Departure and arrival airports must be different.");
        }

        // Check whether the arrival time is after the departure time.
        if (!request.getArrivalTime().isAfter(request.getDepartureTime())){
            throw new BusinessException("Arrival time must be after departure time.");
        }

        // Create a Flight entity using the request data and Airport entities.
        Flight flight = new Flight(request.getFlightNumber(), departureAirport, arrivalAirport, request.getAircraftModel(), request.getDepartureTime(), request.getArrivalTime(), request.getPrice());

        // Save the new Flight entity to the database and return the saved entity.
        return flightRepository.save(flight);
    }

    // Updates an existing flight using the provided information.
    @Transactional
    public Flight updateFlight(Long flightId, FlightUpdateRequest updatedRequest){

        // Retrieve the existing flight from the database.
        Flight flight = flightRepository.findById(flightId).orElseThrow(
                () -> new ResourceNotFoundException("Flight not found with id: " + flightId)
        );

        // Retrieve the Airport entity associated with the updated departure IATA code.
        Airport updatedDepartureAirport = airportRepository.findByIataCode(updatedRequest.getDepartureIataCode()).orElseThrow(
                () -> new ResourceNotFoundException("Departure airport not found with IATA code: " + updatedRequest.getDepartureIataCode())
        );

        // Retrieve the Airport entity associated with the updated arrival IATA code.
        Airport updatedArrivalAirport = airportRepository.findByIataCode(updatedRequest.getArrivalIataCode()).orElseThrow(
                () -> new ResourceNotFoundException("Arrival airport not found with IATA code: " + updatedRequest.getArrivalIataCode())
        );

        if (updatedDepartureAirport.getId().equals(updatedArrivalAirport.getId())){
            throw new BusinessException("Departure and arrival airports must be different.");
        }

        if (!updatedRequest.getArrivalTime().isAfter(updatedRequest.getDepartureTime())){
            throw new BusinessException("Arrival time must be after departure time.");
        }

        // Update the existing flight with the new information.
        flight.setFlightNumber(updatedRequest.getFlightNumber());
        flight.setDepartureAirport(updatedDepartureAirport);
        flight.setArrivalAirport(updatedArrivalAirport);
        flight.setAircraftModel(updatedRequest.getAircraftModel());
        flight.setDepartureTime(updatedRequest.getDepartureTime());
        flight.setArrivalTime(updatedRequest.getArrivalTime());
        flight.setPrice(updatedRequest.getPrice());

        return flight;
    }

}
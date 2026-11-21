package com.flightreservation.service;

import com.flightreservation.entity.Airport;
import com.flightreservation.exception.ResourceNotFoundException;
import com.flightreservation.repository.AirportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository){
        this.airportRepository = airportRepository;
    }

    // Retrieves all airports from the database.
    @Transactional(readOnly = true)
    public List<Airport> getAllAirports(){
        return airportRepository.findAll();
    }

    // Retrieves an airport by its IATA code.
    // Throws ResourceNotFoundException if the airport does not exist.
    @Transactional(readOnly = true)
    public Airport getAirportByIataCode(String iataCode){
        return airportRepository.findByIataCode(iataCode).orElseThrow(
                ()-> new ResourceNotFoundException("Airport not found with IATA code: " +iataCode)
        );
    }
}

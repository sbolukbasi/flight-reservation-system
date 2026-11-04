package com.flightreservation.repository;

import com.flightreservation.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Retrieves the reservations belonging to a specific passenger
    List<Booking> findByPassenger_Id(Long passengerId);

    // Finds booking associated with a specific flight seat ID.
    // Includes 'cancelled' bookings as well
    List<Booking> findByFlightSeat_Id(Long flightSeatId);

    // Checks whether a flight seat has any booking with a status other than the given status
    boolean existsByFlightSeat_IdAndStatusNot(Long flightSeatId, String status);
}

package com.flightreservation.repository;

import com.flightreservation.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    List<Passenger> findByUser_Id(Long userId);
}

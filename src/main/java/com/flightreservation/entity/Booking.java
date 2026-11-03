package com.flightreservation.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // A passenger can have multiple bookings over time
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    // A flight seat can have multiple historical booking records
    // The partial unique index in schema.sql prevents multiple non-cancelled bookings for the same seat
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "flight_seat_id", nullable = false)
    private FlightSeat flightSeat;

    // Stores the flight price at the time of booking
    // This value remains unchanged even if the flight price changes later
    @Column(name = "booked_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal bookedPrice;

    // A new booking has CONFIRMED status by default
    // The status can later change to CANCELLED or COMPLETED
    @Column(nullable = false, length = 20)
    private String status = "CONFIRMED";

    // The database automatically sets the booking time using DEFAULT now()
    // Hibernate does not include this column in INSERT or UPDATE statements
    @Column(name = "booking_time", nullable = false, insertable = false, updatable = false)
    private OffsetDateTime bookingTime;

    protected Booking(){
    }

    // Status is initialized to CONFIRMED by default,
    // so it does not need to be provided as a constructor parameter
    public Booking(Passenger passenger, FlightSeat flightSeat, BigDecimal bookedPrice){
        this.passenger = passenger;
        this.flightSeat = flightSeat;
        this.bookedPrice = bookedPrice;
    }

    public Long getId() {
        return id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public FlightSeat getFlightSeat() {
        return flightSeat;
    }

    public void setFlightSeat(FlightSeat flightSeat) {
        this.flightSeat = flightSeat;
    }

    public BigDecimal getBookedPrice() {
        return bookedPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OffsetDateTime getBookingTime() {
        return bookingTime;
    }
}
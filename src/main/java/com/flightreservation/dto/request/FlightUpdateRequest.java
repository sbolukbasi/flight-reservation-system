package com.flightreservation.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class FlightUpdateRequest {

    @NotBlank
    @Size(max = 20)
    private String flightNumber;

    @NotBlank
    @Pattern(regexp = "[A-Z]{3}")
    private String departureIataCode;

    @NotBlank
    @Pattern(regexp = "[A-Z]{3}")
    private String arrivalIataCode;

    @NotBlank
    @Size(max = 50)
    private String aircraftModel;

    @NotNull
    private OffsetDateTime departureTime;

    @NotNull
    private OffsetDateTime arrivalTime;

    @NotNull
    @PositiveOrZero
    @Digits(integer = 8, fraction = 2)
    private BigDecimal price;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureIataCode() {
        return departureIataCode;
    }

    public void setDepartureIataCode(String departureIataCode) {
        this.departureIataCode = departureIataCode;
    }

    public String getArrivalIataCode() {
        return arrivalIataCode;
    }

    public void setArrivalIataCode(String arrivalIataCode) {
        this.arrivalIataCode = arrivalIataCode;
    }

    public String getAircraftModel() {
        return aircraftModel;
    }

    public void setAircraftModel(String aircraftModel) {
        this.aircraftModel = aircraftModel;
    }

    public OffsetDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(OffsetDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public OffsetDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(OffsetDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

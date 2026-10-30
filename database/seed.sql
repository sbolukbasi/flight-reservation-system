INSERT INTO airports (iata_code, name, city, country)
VALUES ('IST', 'Istanbul Airport', 'Istanbul', 'Türkiye'),
       ('ESB', 'Ankara Esenboga Airport', 'Ankara', 'Türkiye'),
       ('ADB', 'Izmir Adnan Menderes Airport', 'Izmir', 'Türkiye');

INSERT INTO flights (flight_number, departure_airport_id, arrival_airport_id, aircraft_model, departure_time, arrival_time, price)
VALUES ('TK123',
        -- Finds the departure airport ID using its unique IATA code.
        (SELECT id FROM airports WHERE iata_code = 'IST'),
        (SELECT id FROM airports WHERE iata_code = 'ESB'),
        'Boeing 737',
        -- Used +03 for Turkish time zone.
        '2026-11-26 09:00:00+03',
        '2026-11-26 10:05:00+03',
        2500.00
       ),
       ('TK415',
        (SELECT id FROM airports WHERE iata_code = 'ESB'),
        (SELECT id FROM airports WHERE iata_code = 'ADB'),
        'Airbus A320',
        '2026-11-30 14:00:00+03',
        '2026-11-30 15:20:00+03',
        2150.00
      );

INSERT INTO flight_seats (flight_id, seat_number)
VALUES (
        -- Finds the flight ID using its flight number and departure time.
        (SELECT id FROM flights WHERE flight_number = 'TK123' AND departure_time = '2026-11-26 09:00:00+03'),
        -- Determines the seat number to be assigned for the relevant flight.
        '1A'
       ),
       (
        (SELECT id FROM flights WHERE flight_number = 'TK123' AND departure_time = '2026-11-26 09:00:00+03'),
        '1B'
       ),
       (
        (SELECT id FROM flights WHERE flight_number = 'TK123' AND departure_time = '2026-11-26 09:00:00+03'),
        '12A'
       ),
       (
        (SELECT id FROM flights WHERE flight_number = 'TK415' AND departure_time = '2026-11-30 14:00:00+03'),
        '1A'
       ),
       (
        (SELECT id FROM flights WHERE flight_number = 'TK415' AND departure_time = '2026-11-30 14:00:00+03'),
        '1B'
       ),
       (
        (SELECT id FROM flights WHERE flight_number = 'TK415' AND departure_time = '2026-11-30 14:00:00+03'),
        '12A'
       );

-- Demo accounts for local development only.
-- Both demo accounts use the test password: password123.
-- Passwords are stored as BCrypt hashes, not plain text.
INSERT INTO users (email, password_hash, role)
VALUES (
           'demo.user@example.com',
           '$2y$10$6lh508Lf/5impZ8sGDlpW.ogrZWn2hyTObg9JpFMKncKvPKzi8yca',
           'USER'
       ),
       (
           'demo.admin@example.com',
           '$2y$10$VJSsZosSkwgfLJtg6UL2p.gL9rC5GHUYRNY6K30as9j6GmREqOoXe',
           'ADMIN'
       );

INSERT INTO passengers (user_id, first_name, last_name)
VALUES (
        (SELECT id FROM users WHERE email = 'demo.user@example.com'),
        'Ali',
        'Kaya'
       ),
       (
        (SELECT id FROM users WHERE email = 'demo.admin@example.com'),
        'Kenan',
        'Demir'
       );

INSERT INTO bookings (passenger_id, flight_seat_id, booked_price, status)
VALUES (
        (SELECT id FROM passengers WHERE first_name = 'Ali' AND last_name = 'Kaya'),
        -- Finds the ID of seat 1A on flight TK123.
        -- First, finds the flight ID, then uses it to find the seat ID.
        (SELECT id FROM flight_seats WHERE flight_id = (SELECT id FROM flights WHERE flight_number = 'TK123' AND departure_time = '2026-11-26 09:00:00+03') AND seat_number = '1A'),
        -- Preserves the price at the time of booking.
        (SELECT price FROM flights WHERE flight_number = 'TK123' AND departure_time = '2026-11-26 09:00:00+03'),
        'CONFIRMED'
       ),
      (
       (SELECT id FROM passengers WHERE first_name = 'Ali' and last_name = 'Kaya'),
       (SELECT id FROM flight_seats WHERE flight_id = (SELECT id FROM flights WHERE flight_number = 'TK123' AND departure_time = '2026-11-26 09:00:00+03') AND seat_number = '1A'),
       (SELECT price FROM flights WHERE flight_number = 'TK123' AND departure_time = '2026-11-26 09:00:00+03'),
       'CANCELLED'
      );
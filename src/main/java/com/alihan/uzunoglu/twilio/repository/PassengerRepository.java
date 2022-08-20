package com.alihan.uzunoglu.twilio.repository;

import com.alihan.uzunoglu.twilio.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}

package com.alihan.uzunoglu.twilio.repository;

import com.alihan.uzunoglu.twilio.entity.Driver;
import com.alihan.uzunoglu.twilio.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    Set<Passenger> findAllByDriver(Driver driver);
}

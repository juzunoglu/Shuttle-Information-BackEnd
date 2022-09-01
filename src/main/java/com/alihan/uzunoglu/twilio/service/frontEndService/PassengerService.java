package com.alihan.uzunoglu.twilio.service.frontEndService;

import com.alihan.uzunoglu.twilio.entity.Passenger;

import java.util.List;

public interface PassengerService {

    boolean assignPassengerToDriver(Long driverId, Passenger passenger);

    boolean unAssignPassengerFromDriver(Long id);

    Passenger savePassenger(Passenger passenger);

    List<Passenger> findAll();

    boolean deletePassengerById(Long id);

    Passenger updatePassenger(Long id, Passenger updatedPassenger);

}


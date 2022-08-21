package com.alihan.uzunoglu.twilio.service.frontEndService;

import com.alihan.uzunoglu.twilio.entity.Passenger;

public interface PassengerService {

    boolean assignPassengerToDriver(Long id, Passenger passenger);
}


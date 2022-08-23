package com.alihan.uzunoglu.twilio.service.frontEndService;

import com.alihan.uzunoglu.twilio.entity.Driver;
import com.alihan.uzunoglu.twilio.entity.Passenger;
import com.alihan.uzunoglu.twilio.repository.DriverRepo;
import com.alihan.uzunoglu.twilio.repository.PassengerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class PassengerServiceImpl implements PassengerService {


    private final DriverRepo driverRepo;
    private final PassengerRepository passengerRepository;

    public PassengerServiceImpl(DriverRepo driverRepo, PassengerRepository passengerRepository) {
        this.driverRepo = driverRepo;
        this.passengerRepository = passengerRepository;
    }

    @Override
    public boolean assignPassengerToDriver(Long id, Passenger passenger) {
        Driver driver = driverRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No driver by the id: " + id));
        Passenger passenger1 = passengerRepository.findById(passenger.getId())
                        .orElseThrow(() -> new RuntimeException("No passenger " + id));
        passenger1.setDriver(driver);
        return true;
    }
}

package com.alihan.uzunoglu.twilio.service.frontEndService;

import com.alihan.uzunoglu.twilio.entity.Driver;
import com.alihan.uzunoglu.twilio.entity.Passenger;
import com.alihan.uzunoglu.twilio.repository.DriverRepo;
import com.alihan.uzunoglu.twilio.repository.PassengerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class PassengerServiceImpl implements PassengerService {


    private final DriverRepo driverRepo;

    public PassengerServiceImpl(DriverRepo driverRepo) {
        this.driverRepo = driverRepo;
    }

    @Override
    public boolean assignPassengerToDriver(Long id, Passenger passenger) {
        Driver driver = driverRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No driver by the id: " + id));
        driver.setPassengers(List.of(passenger));
        return true;
    }
}

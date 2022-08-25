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

    @Override
    public boolean unAssignPassengerFromDriver(Long id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Passenger is found with id: " + id));
        passenger.setDriver(null);
        return true;
    }

    @Override
    public Passenger save(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    @Override
    public boolean deletePassengerById(Long id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No passenger exists with id: " + id));
        passengerRepository.delete(passenger);
        return true;
    }
}

package com.alihan.uzunoglu.twilio.service.frontEndService;

import com.alihan.uzunoglu.twilio.entity.Driver;
import com.alihan.uzunoglu.twilio.entity.Passenger;
import com.alihan.uzunoglu.twilio.entity.Route;
import com.alihan.uzunoglu.twilio.repository.DriverRepo;
import com.alihan.uzunoglu.twilio.repository.PassengerRepository;
import com.alihan.uzunoglu.twilio.repository.RouteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class PassengerServiceImpl implements PassengerService {

    private final DriverRepo driverRepo;
    private final PassengerRepository passengerRepository;

    private final RouteRepo routeRepo;

    @Autowired
    public PassengerServiceImpl(DriverRepo driverRepo, PassengerRepository passengerRepository, RouteRepo routeRepo) {
        this.driverRepo = driverRepo;
        this.passengerRepository = passengerRepository;
        this.routeRepo = routeRepo;
    }

    @Override
    public boolean assignPassengerToDriver(Long driverId, Passenger passenger) {
        Driver driver = driverRepo.findById(driverId)
                .orElseThrow(() -> new RuntimeException("No driver by the id: " + driverId));
        Passenger passenger1 = passengerRepository.findById(passenger.getId())
                .orElseThrow(() -> new RuntimeException("No passenger " + driverId));
        passenger1.setDriver(driver);
        if (driver.getRoute() != null) {
            routeRepo.delete(driver.getRoute());
        }
        Route route = new Route(driver.getLatitude(), driver.getLongitude(), passenger1.getLatitude(), passenger1.getLongitude());
        driver.setRoute(route);
        return true;
    }

    @Override
    public boolean unAssignPassengerFromDriver(Long id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Passenger is found with id: " + id));
        routeRepo.delete(passenger.getDriver().getRoute());
        passenger.getDriver().setRoute(null);
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

package com.alihan.uzunoglu.twilio.service.frontEndService;

import com.alihan.uzunoglu.twilio.entity.Driver;
import com.alihan.uzunoglu.twilio.entity.Passenger;
import com.alihan.uzunoglu.twilio.entity.Route;
import com.alihan.uzunoglu.twilio.model.DriverDTO;
import com.alihan.uzunoglu.twilio.model.RouteDTO;
import com.alihan.uzunoglu.twilio.repository.DriverRepo;
import com.alihan.uzunoglu.twilio.repository.PassengerRepository;
import com.alihan.uzunoglu.twilio.repository.RouteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class DriverStorageServiceImpl implements DriverStorageService {
    private final DriverRepo driverRepo;
    private final PassengerRepository passengerRepository;
    private final RouteRepo routeRepo;

    @Autowired
    public DriverStorageServiceImpl(DriverRepo driverRepo, PassengerRepository passengerRepository, RouteRepo routeRepo) {
        this.driverRepo = driverRepo;
        this.passengerRepository = passengerRepository;
        this.routeRepo = routeRepo;
    }

    @Override
    public Driver save(DriverDTO driverDTO) {
        Driver driver = new Driver(
                driverDTO.name(),
                driverDTO.age(),
                driverDTO.phoneNumber(),
                driverDTO.experience(),
                driverDTO.email(),
                driverDTO.carMake(),
                driverDTO.carModel(),
                driverDTO.carTag(),
                driverDTO.latitude(),
                driverDTO.longitude()
        );
        return driverRepo.save(driver);
    }

    @Override
    public Driver uploadCarPhoto(Long id, MultipartFile file) throws IOException {
        Driver driver = driverRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No driver is found. Please create a driver first!"));
        driver.setCarPhoto(file.getBytes());
        return driverRepo.save(driver);
    }


    @Override
    public byte[] getCarPhoto(Long id) {
        return driverRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No Driver is found"))
                .getCarPhoto();
    }

    @Override
    public List<Driver> getAllDrivers() {
        int size = driverRepo.findAll().size(); // todo do pagination
        return driverRepo.findAll().subList(0, size);
    }

    // When deleting driver -> passenger should not be deleted
    @Override
    public boolean deleteDriverById(Long id) {
        Driver driver = driverRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No driver by the id: " + id));
        Set<Passenger> passengers = driver.getPassengers();
        passengers.forEach(passenger -> {
            Passenger assignedPass = passengerRepository.findById(passenger.getId())
                    .orElseThrow(() -> new RuntimeException("No pass with id: " + id));
            assignedPass.setDriver(null);
        });
        driverRepo.delete(driver);
        return true;
    }

    @Override
    public Set<Passenger> getAssociatedPassengers(Long driverId) {
        Driver driver = driverRepo.findById(driverId)
                .orElseThrow(() -> new RuntimeException("No driver found with driverId: " + driverId));
        return driver.getPassengers();
    }

    @Override
    public Driver getDriverByPassengerId(Long passengerId) {
        return driverRepo.getAssignedDriverByPassengerId(passengerId)
                .orElse(null);
    }
}

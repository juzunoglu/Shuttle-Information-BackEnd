package com.alihan.uzunoglu.twilio.service.frontEndService;

import com.alihan.uzunoglu.twilio.entity.Driver;
import com.alihan.uzunoglu.twilio.entity.Passenger;
import com.alihan.uzunoglu.twilio.model.DriverDTO;
import com.alihan.uzunoglu.twilio.repository.DriverRepo;
import com.alihan.uzunoglu.twilio.repository.PassengerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DriverStorageServiceImpl implements DriverStorageService {
    private final DriverRepo driverRepo;

    private final PassengerRepository passengerRepository;

    public DriverStorageServiceImpl(DriverRepo driverRepo, PassengerRepository passengerRepository) {
        this.driverRepo = driverRepo;
        this.passengerRepository = passengerRepository;
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

    @Override
    public boolean deleteDriverById(Long id) {
        Driver driver = driverRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No driver by the id: " + id));
        try {
            driverRepo.delete(driver);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Driver assignPassengerToDriver(Long id, Passenger passenger) {
        return driverRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No driver found by the id: " + id));
    }
}

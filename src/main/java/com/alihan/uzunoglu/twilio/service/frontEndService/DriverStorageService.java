package com.alihan.uzunoglu.twilio.service.frontEndService;

import com.alihan.uzunoglu.twilio.entity.Driver;
import com.alihan.uzunoglu.twilio.entity.Passenger;
import com.alihan.uzunoglu.twilio.model.DriverDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface DriverStorageService {


	Driver save(DriverDTO driverDTO);
	Driver uploadCarPhoto(Long id, MultipartFile file) throws IOException;

	byte[] getCarPhoto(Long id);

	List<Driver> getAllDrivers();

	boolean deleteDriverById(Long id);

	Set<Passenger> getAssociatedPassengers(Long id);

	Driver assignPassengerToDriver(Long id, Passenger passenger);

}

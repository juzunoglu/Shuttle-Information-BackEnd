package com.alihan.uzunoglu.twilio.service.frontEndService;

import com.alihan.uzunoglu.twilio.entity.Driver;
import com.alihan.uzunoglu.twilio.model.DriverDTO;
import com.alihan.uzunoglu.twilio.repository.DriverRepo;
import com.alihan.uzunoglu.twilio.service.frontEndService.DriverStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class DriverStorageServiceImpl implements DriverStorageService {
	private final DriverRepo driverRepo;

	public DriverStorageServiceImpl(DriverRepo driverRepo) {
		this.driverRepo = driverRepo;
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
						driverDTO.carTag()
		);
		return driverRepo.save(driver);
	}

	@Override
	public Driver uploadCarPhoto(Long id, MultipartFile file) throws IOException {
		Driver driver = driverRepo.findById(id)
						.orElseThrow(() -> new RuntimeException("No driver is found"));
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
	public Stream<Driver> getAllPhotos() {
		return driverRepo.findAll().stream();
	}
}
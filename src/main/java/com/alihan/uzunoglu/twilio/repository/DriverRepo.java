package com.alihan.uzunoglu.twilio.repository;

import com.alihan.uzunoglu.twilio.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepo extends JpaRepository<Driver, Long> {

	Driver findByDriverPhoto(byte[] driverPhoto);

}

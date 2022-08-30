package com.alihan.uzunoglu.twilio.repository;

import com.alihan.uzunoglu.twilio.entity.Driver;
import com.alihan.uzunoglu.twilio.entity.Route;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DriverRepo extends JpaRepository<Driver, Long> {
    Driver findByDriverPhoto(byte[] driverPhoto);

    Driver findDriverByPassengers_Id(Long passengers_id);

    @Query(
            value = "SELECT * FROM Driver d JOIN Passenger p ON d.id = p.driver_id WHERE p.id = :passenger_id",
            nativeQuery = true)
    Optional<Driver> getAssignedDriverByPassengerId(@Param("passenger_id") Long id);

    List<Driver> findDriversByRoute(Route route);

    List<Driver> findDriversByRouteIsNotNull();
}

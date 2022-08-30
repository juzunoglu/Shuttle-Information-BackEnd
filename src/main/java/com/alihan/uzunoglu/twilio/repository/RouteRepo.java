package com.alihan.uzunoglu.twilio.repository;

import com.alihan.uzunoglu.twilio.entity.Driver;
import com.alihan.uzunoglu.twilio.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepo extends JpaRepository<Route, Long> {

    Route findRouteByDriver(Driver driver);
    List<Route> findRoutesByDriverLatitudeIsNotNullAndDriverLongitudeIsNotNullAndPassengerLatitudeIsNotNullAndPassengerLongitudeIsNotNull();
}

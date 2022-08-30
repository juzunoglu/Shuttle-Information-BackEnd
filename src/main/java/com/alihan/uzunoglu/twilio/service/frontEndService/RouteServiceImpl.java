package com.alihan.uzunoglu.twilio.service.frontEndService;

import com.alihan.uzunoglu.twilio.entity.Driver;
import com.alihan.uzunoglu.twilio.entity.Route;
import com.alihan.uzunoglu.twilio.repository.DriverRepo;
import com.alihan.uzunoglu.twilio.repository.RouteRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class RouteServiceImpl implements RouteService {

    private final RouteRepo routeRepo;

    private final DriverRepo driverRepo;

    @Autowired
    public RouteServiceImpl(RouteRepo routeRepo, DriverRepo driverRepo) {
        this.routeRepo = routeRepo;
        this.driverRepo = driverRepo;
    }

    @Override
    public Route saveRoute(Route route) {
        return routeRepo.save(route);
    }

    @Override
    public Route updateRoute(Route route) {
        return null;
    }

    @Override
    public boolean deleteRoute(Route route) {
        if (routeRepo.existsById(route.getId())) {
            routeRepo.delete(route);
            return true;
        }
        return false;
    }


    @Override
    public Route getRouteByDriverId(Long driverId) {
        Driver driver = driverRepo.findById(driverId).orElseThrow(() -> new RuntimeException("asda"));
        return routeRepo.findRouteByDriver(driver);
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepo.findRoutesByDriverLatitudeIsNotNullAndDriverLongitudeIsNotNullAndPassengerLatitudeIsNotNullAndPassengerLongitudeIsNotNull();

    }
}

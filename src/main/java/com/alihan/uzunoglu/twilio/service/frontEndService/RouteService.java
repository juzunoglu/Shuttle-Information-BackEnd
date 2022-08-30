package com.alihan.uzunoglu.twilio.service.frontEndService;

import com.alihan.uzunoglu.twilio.entity.Driver;
import com.alihan.uzunoglu.twilio.entity.Route;

import java.util.List;

public interface RouteService {

    Route saveRoute(Route route);

    Route updateRoute(Route route);

    boolean deleteRoute(Route route);

    Route getRouteByDriverId(Long driverId);

    List<Route> getAllRoutes();

}

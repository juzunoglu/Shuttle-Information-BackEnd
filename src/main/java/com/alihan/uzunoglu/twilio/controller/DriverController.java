package com.alihan.uzunoglu.twilio.controller;


import com.alihan.uzunoglu.twilio.entity.Driver;
import com.alihan.uzunoglu.twilio.entity.Passenger;
import com.alihan.uzunoglu.twilio.entity.Route;
import com.alihan.uzunoglu.twilio.model.DriverDTO;
import com.alihan.uzunoglu.twilio.security.payload.response.MessageResponse;
import com.alihan.uzunoglu.twilio.service.frontEndService.DriverStorageService;
import com.alihan.uzunoglu.twilio.service.frontEndService.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/driver")
@Transactional
@Slf4j
public class DriverController {

    private final DriverStorageService driverStorageService;

    private final RouteService routeService;
    @Autowired
    public DriverController(DriverStorageService driverStorageService, RouteService routeService) {
        this.driverStorageService = driverStorageService;
        this.routeService = routeService;
    }

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Driver> saveDriver(@Validated @RequestBody DriverDTO driverDTO) {
        log.info("saveDriver() is called with dto: {}", driverDTO);
        Driver driver = driverStorageService.save(driverDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(driver);
    }

    @PostMapping(value = "/{id}/addPhoto", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MessageResponse> createDriverPhoto(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
        log.info("createDriverPhoto() is called with file: {}", file.getOriginalFilename());
        String response;
        try {
            driverStorageService.uploadCarPhoto(id, file);
            response = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new MessageResponse(response));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/carPhoto/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getCarPhoto(@PathVariable Long id) {
        log.info("getCarPhoto() is called with id: {}", id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(driverStorageService.getCarPhoto(id));
    }

    @GetMapping(value = "/getAllDrivers")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        log.info("getAllDrivers() is called");
        return ResponseEntity
                .ok()
                .body(driverStorageService.getAllDrivers());
    }

    @GetMapping(value = "/getAssociatedPassengers/{id}")
    public ResponseEntity<Set<Passenger>> getAssociatedPassengers(@PathVariable Long id) {
        log.info("getAssociatedPassengers() is called with id: {}", id);
        return ResponseEntity
                .ok()
                .body(driverStorageService.getAssociatedPassengers(id));
    }

    @GetMapping(value = "/getAssignedDriverByPassengerId/{passengerId}")
    public ResponseEntity<?> getAssignedDriverByPassengerId(@PathVariable Long passengerId) {
        log.info("getAssignedDriverByPassengerId is called with passengerId: {}", passengerId);
        if (driverStorageService.getDriverByPassengerId(passengerId) == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No driver is assigned to the passenger");
        }
        return ResponseEntity
                .ok()
                .body(driverStorageService.getDriverByPassengerId(passengerId));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> deleteDriverById(@PathVariable Long id) {
        log.info("deleteDriverById() is called with id: {}", id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(driverStorageService.deleteDriverById(id));
    }

    @GetMapping(value = "/getRoutes")
    public ResponseEntity<List<Route>> getDriverRoutes() {
        log.info("getDriverRoutes() is called");
        List<Route> routeList = routeService.getAllRoutes();
		return ResponseEntity
				.ok()
				.body(routeList);
    }
}

package com.alihan.uzunoglu.twilio.controller;

import com.alihan.uzunoglu.twilio.entity.Passenger;
import com.alihan.uzunoglu.twilio.repository.PassengerRepository;
import com.alihan.uzunoglu.twilio.service.frontEndService.PassengerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/passenger")
@Transactional
@Slf4j
public class PassengerController {

    private final PassengerRepository passengerRepository;
    private final PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerRepository passengerRepository, PassengerService passengerService) {
        this.passengerRepository = passengerRepository;
        this.passengerService = passengerService;
    }

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Passenger> savePassenger(@Validated @RequestBody Passenger passenger) {
        log.info("savePassenger() is called with dto: {}", passenger);
        Passenger toBeSaved = passengerRepository.save(passenger);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toBeSaved);
    }

    @GetMapping(value = "/getAllPassengers")
    public ResponseEntity<List<Passenger>> getAllPassengers() {
        log.info("getAllPassengers() is called with");
        return ResponseEntity
                .ok()
                .body(passengerRepository.findAll());
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<Boolean> deletePassengerById(@PathVariable Long id) {
        log.info("deletePassengerById() is called with id: {}", id);
        passengerRepository.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(true);
    }

    @PostMapping(value = "/assignToDriver/{id}")
    public ResponseEntity<Boolean> assignPassengerToDriver(@PathVariable Long id, @Validated @RequestBody Passenger passenger) {
        log.info("assignPassengerToDriver() is called with id: {} and passenger: {}", id, passenger);
        boolean res = passengerService.assignPassengerToDriver(id, passenger);
        return ResponseEntity
                .ok()
                .body(res);
    }
}

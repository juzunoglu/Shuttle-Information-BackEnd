package com.alihan.uzunoglu.twilio.controller;

import com.alihan.uzunoglu.twilio.entity.Passenger;
import com.alihan.uzunoglu.twilio.repository.PassengerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class PassengerController {

    private final static Logger LOGGER = LoggerFactory.getLogger(PassengerController.class);

    private final PassengerRepository passengerRepository;

    @Autowired
    public PassengerController(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Passenger> savePassenger(@Validated @RequestBody Passenger passenger) {
        LOGGER.info("savePassenger() is called with dto: {}", passenger);
        Passenger toBeSaved = passengerRepository.save(passenger);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toBeSaved);
    }

    @GetMapping(value = "/getAllPassengers")
    public ResponseEntity<List<Passenger>> getAllPassengers() {
        return ResponseEntity
                .ok()
                .body(passengerRepository.findAll());
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<Boolean> deletePassengerById(@PathVariable Long id) {
        passengerRepository.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(true);
    }
}

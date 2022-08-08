package com.alihan.uzunoglu.twilio.controller;


import com.alihan.uzunoglu.twilio.entity.Driver;
import com.alihan.uzunoglu.twilio.model.DriverDTO;
import com.alihan.uzunoglu.twilio.security.payload.response.MessageResponse;
import com.alihan.uzunoglu.twilio.service.frontEndService.DriverStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/driver")
@Transactional
public class DriverController {

	private final static Logger LOGGER = LoggerFactory.getLogger(DriverController.class);

	private final DriverStorageService driverStorageService;

	public DriverController(DriverStorageService driverStorageService) {
		this.driverStorageService = driverStorageService;
	}

	@PostMapping(value = "/create", consumes = {
					MediaType.MULTIPART_FORM_DATA_VALUE,
					MediaType.APPLICATION_JSON_VALUE}
	)
	public ResponseEntity<MessageResponse> createDriver(@RequestPart("json") @Validated DriverDTO driverDTO, @RequestPart("file") MultipartFile file) {
		LOGGER.info("createDriver() is called with dto: {} and file: {}", driverDTO, file);
		String response;
		try {
			Driver driver = driverStorageService.save(driverDTO);
			driverStorageService.uploadCarPhoto(driver.getId(), file);
			response = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity
							.status(HttpStatus.CREATED)
							.body(new MessageResponse(response));
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@GetMapping(value = "/carPhoto/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getCarPhoto(@PathVariable Long id) {
		LOGGER.info("getCarPhoto() is called with id: {}", id);
		return ResponseEntity
						.ok()
						.contentType(MediaType.IMAGE_JPEG)
						.body(driverStorageService.getCarPhoto(id));
	}
}
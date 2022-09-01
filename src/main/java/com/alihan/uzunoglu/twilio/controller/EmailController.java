package com.alihan.uzunoglu.twilio.controller;

import com.alihan.uzunoglu.twilio.service.EmailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/email")
public class EmailController {

	private final static Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

	private final EmailSenderService emailSenderService;

	@Autowired
	public EmailController(EmailSenderService emailSenderService) {
		this.emailSenderService = emailSenderService;
	}

	// TODO make this a Schedule Job to remind that he/she will be using the shuttle tmrrw.
	@PostMapping("/sendEmail")
	public ResponseEntity<SimpleMailMessage> sendEmail(@RequestBody SimpleMailMessage email) {
		LOGGER.info("sendEmail({}) is called", email);
		SimpleMailMessage result =
						this.emailSenderService.sendEmail(Arrays.stream(Objects.requireNonNull(email.getTo())).findFirst().get(), email.getSubject(), email.getText());
		return ResponseEntity.ok(result);
	}
}

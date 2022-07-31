package com.alihan.uzunoglu.twilio.controller;
import com.alihan.uzunoglu.twilio.model.SmsRequest;
import com.alihan.uzunoglu.twilio.service.SmsSender;
import com.alihan.uzunoglu.twilio.service.SmsSenderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/sms")
public class SmsController {

	private final static Logger LOGGER = LoggerFactory.getLogger(SmsController.class);
	private final SmsSender smsSender;

	@Autowired
	public SmsController(SmsSender smsSender) {
		this.smsSender = smsSender;
	}

	@GetMapping("/isUp")
	public ResponseEntity<String> isUp() {
		LOGGER.info("The server is UP");
		return ResponseEntity.ok("OK!");
	}

	@PostMapping("/sendSms")
	public ResponseEntity<SmsRequest> sendSms(@Validated @RequestBody SmsRequest smsRequest) {
		LOGGER.info("sendSms({}) is called", smsRequest);
		return ResponseEntity.ok(smsSender.sendSms(smsRequest));
	}
}

package com.alihan.uzunoglu.twilio.controller;
import com.alihan.uzunoglu.twilio.model.SmsRequestDTO;
import com.alihan.uzunoglu.twilio.service.SmsSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

	@PostMapping("/sendSms")
	public ResponseEntity<SmsRequestDTO> sendSms(@Validated @RequestBody SmsRequestDTO smsRequestDTO) {
		LOGGER.info("sendSms({}) is called", smsRequestDTO);
		return ResponseEntity.ok(smsSender.sendSms(smsRequestDTO));
	}
}

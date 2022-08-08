package com.alihan.uzunoglu.twilio.controller;

import com.alihan.uzunoglu.twilio.exception.EmailSendException;
import com.alihan.uzunoglu.twilio.service.frontEndService.UserInfoService;
import com.alihan.uzunoglu.twilio.model.UserShuttleInfoDTO;
import com.alihan.uzunoglu.twilio.model.SmsRequestDTO;
import com.alihan.uzunoglu.twilio.service.EmailSenderService;
import com.alihan.uzunoglu.twilio.service.SmsSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/user")
public class UserShuttleInfoController {

	private final static String messageBody = "I'm using the shuttle tomorrow.";
	private final static String messageTo = "whatsapp:+905527040545";
	private final static String emailSubject = "Shuttle Service";
	private final static Logger LOGGER = LoggerFactory.getLogger(UserShuttleInfoController.class);
	private final UserInfoService userInfoService;
	private final SmsSender smsSender;

	private final EmailSenderService emailSenderService;


	private String emailMessageBuilder(String emailMessageBody) {
		return "Your driver will pick you up on: " +
						emailMessageBody;
	}
	@Autowired
	public UserShuttleInfoController(UserInfoService userInfoService,
																	 SmsSender smsSender,
																	 EmailSenderService emailSenderService) {
		this.userInfoService = userInfoService;
		this.smsSender = smsSender;
		this.emailSenderService = emailSenderService;
	}
	@PostMapping("/getUserInfo")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<UserShuttleInfoDTO> getUserInfo(@RequestBody UserShuttleInfoDTO userShuttleInfoDTO) {
		LOGGER.info("getUserInfo({}) is called", userShuttleInfoDTO);
		UserShuttleInfoDTO result = userInfoService.getUserInfo(userShuttleInfoDTO);
		SmsRequestDTO toBeSent = new SmsRequestDTO(messageTo, messageBody, result.latitude(), result.longitude());
		LOGGER.info("Sms to be sent: {}", toBeSent);
		try {
			// todo send the mail as a scheduler to remind the user one day before at night at 11:00PM
			SimpleMailMessage mailToBeSent = emailSenderService.sendEmail(userShuttleInfoDTO.email(), emailSubject,
							emailMessageBuilder(userShuttleInfoDTO.comingDates().stream()
											.map(Date::toGMTString)
											.collect(Collectors.joining())));
			LOGGER.info("Email to be sent: {}", mailToBeSent);
		} catch (EmailSendException e) {
			LOGGER.warn("Exception occurred", e);
		}
//		smsSender.sendSms(toBeSent);
		return ResponseEntity.ok(result);
	}
}

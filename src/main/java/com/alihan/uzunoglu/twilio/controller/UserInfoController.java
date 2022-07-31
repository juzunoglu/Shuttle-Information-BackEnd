package com.alihan.uzunoglu.twilio.controller;

import com.alihan.uzunoglu.twilio.exception.EmailSendException;
import com.alihan.uzunoglu.twilio.frontEndService.UserInfoService;
import com.alihan.uzunoglu.twilio.model.UserInfo;
import com.alihan.uzunoglu.twilio.model.SmsRequest;
import com.alihan.uzunoglu.twilio.service.EmailSenderService;
import com.alihan.uzunoglu.twilio.service.SmsSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserInfoController {

	private final static String messageBody = "I'm using the shuttle tomorrow.";
	private final static String messageTo = "whatsapp:+905527040545";
	private final static String emailSubject = "Shuttle Service";
	private final static String emailMessageBody =
					"Please remember tomorrow morning your driver will pick" +
					"you up from the location you entered";
	private final static Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);
	private final UserInfoService userInfoService;
	private final SmsSender smsSender;

	private final EmailSenderService emailSenderService;

	@Autowired
	public UserInfoController(UserInfoService userInfoService,
														SmsSender smsSender,
														EmailSenderService emailSenderService) {
		this.userInfoService = userInfoService;
		this.smsSender = smsSender;
		this.emailSenderService = emailSenderService;
	}

	@PostMapping("/getUserInfo")
	public ResponseEntity<UserInfo> getUserInfo(@RequestBody UserInfo userInfo) {
		LOGGER.info("getUserInfo({}) is called", userInfo);
		UserInfo result = userInfoService.getUserInfo(userInfo);
		SmsRequest toBeSent = new SmsRequest(messageTo, messageBody, result.latitude(), result.longitude());
		LOGGER.info("Sms to be sent: {}", toBeSent);
		try {
			SimpleMailMessage mailToBeSent = emailSenderService.sendEmail(userInfo.email(), emailSubject, emailMessageBody);
			LOGGER.info("Email to be sent: {}", mailToBeSent);
		} catch (EmailSendException e) {
			LOGGER.warn("Exception occurred", e);
		}
//		smsSender.sendSms(toBeSent);
		return ResponseEntity.ok(result);
	}
}

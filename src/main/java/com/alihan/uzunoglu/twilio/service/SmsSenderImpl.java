package com.alihan.uzunoglu.twilio.service;

import com.alihan.uzunoglu.twilio.configuration.TwilioConfiguration;
import com.alihan.uzunoglu.twilio.exception.SendSmsException;
import com.alihan.uzunoglu.twilio.model.SmsRequest;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SmsSenderImpl implements SmsSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmsSenderImpl.class);
	private final TwilioConfiguration twilioConfiguration;


	@Autowired
	public SmsSenderImpl(TwilioConfiguration twilioConfiguration) {
		this.twilioConfiguration = twilioConfiguration;
	}

	@Override
	public SmsRequest sendSms(SmsRequest smsRequest) {
		PhoneNumber to = new PhoneNumber(smsRequest.phoneNumber());
		PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
		String messageBody = smsRequest.message();
		try {
			MessageCreator creator = Message.creator(to, from, messageBody);
			if (smsRequest.latitude() != null && smsRequest.longitude() != null) {
				creator.setPersistentAction(List.of("geo:" + smsRequest.latitude() + "," + smsRequest.longitude()));
			}
			creator.create();
			LOGGER.info("Sent sms is: {}", smsRequest);
			return smsRequest;
		}
		catch (Exception e) {
			throw new SendSmsException("Exception occurred while sending an SMS " + e);
		}
	}

	private boolean isPhoneNumberValid(String phoneNumber) { // TODO
		return true;
	}
}

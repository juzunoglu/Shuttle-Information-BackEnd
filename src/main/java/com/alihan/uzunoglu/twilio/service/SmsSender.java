package com.alihan.uzunoglu.twilio.service;

import com.alihan.uzunoglu.twilio.exception.SendSmsException;
import com.alihan.uzunoglu.twilio.model.SmsRequestDTO;

public interface SmsSender {

	SmsRequestDTO sendSms(SmsRequestDTO smsRequestDTO) throws SendSmsException;

}

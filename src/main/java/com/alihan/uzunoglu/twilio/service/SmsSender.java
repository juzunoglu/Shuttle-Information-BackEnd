package com.alihan.uzunoglu.twilio.service;

import com.alihan.uzunoglu.twilio.exception.SendSmsException;
import com.alihan.uzunoglu.twilio.model.SmsRequest;

public interface SmsSender {

	SmsRequest sendSms(SmsRequest smsRequest) throws SendSmsException;

}

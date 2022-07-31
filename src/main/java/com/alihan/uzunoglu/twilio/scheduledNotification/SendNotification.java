package com.alihan.uzunoglu.twilio.scheduledNotification;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.alihan.uzunoglu.twilio.model.SmsRequest;
import com.alihan.uzunoglu.twilio.service.SmsSenderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component()
public class SendNotification {

	private static final Logger log = LoggerFactory.getLogger(SendNotification.class);

	private final SmsSenderImpl smsSender;

	@Autowired
	public SendNotification(SmsSenderImpl smsSender) {
		this.smsSender = smsSender;
	}

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");



//	 Between 07:00 PM and 08:59 PM, Monday through Friday -> 0 0 19-20 * * MON-FRI
//	 Every min -> 0 * * * * *
	@Scheduled(cron = "0 0 9-17 * * MON-FRI")
	public void sendNotification() { //0 0 9-17 * * MON-FRI
		log.info("The time is now {}", dateFormat.format(new Date()));
		SmsRequest smsRequest = new SmsRequest("whatsapp:+905527040545", "https://www.youtube.com/watch?v=g9YlblQDGos", null, null);
//		smsSender.sendSms(smsRequest);
	}
}

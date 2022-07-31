package com.alihan.uzunoglu.twilio.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailSenderService {
	SimpleMailMessage sendEmail(String to, String subject, String messageBody);
}

package com.alihan.uzunoglu.twilio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

	private final JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String from;

	@Autowired
	public EmailSenderServiceImpl(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public SimpleMailMessage sendEmail(String to, String subject, String messageBody) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(messageBody);

		this.mailSender.send(simpleMailMessage);
		return simpleMailMessage;
	}
}

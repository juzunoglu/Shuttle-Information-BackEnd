package com.alihan.uzunoglu.twilio.exception;

public class SendSmsException extends RuntimeException {

	public SendSmsException(String errorMessage) {
		super(errorMessage);
	}
}

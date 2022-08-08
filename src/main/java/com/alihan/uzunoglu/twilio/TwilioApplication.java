package com.alihan.uzunoglu.twilio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TwilioApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwilioApplication.class, args);
	}

}

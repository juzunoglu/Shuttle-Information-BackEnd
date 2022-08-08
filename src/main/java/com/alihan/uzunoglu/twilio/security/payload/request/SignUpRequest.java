package com.alihan.uzunoglu.twilio.security.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SignUpRequest(

				@JsonProperty("name") String name,
				@JsonProperty("username") String username,
				@JsonProperty("email") String email,
				@JsonProperty("password") String password,
				@JsonProperty("role") List<String> role
) {
}

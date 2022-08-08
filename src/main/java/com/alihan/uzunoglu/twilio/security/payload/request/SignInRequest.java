package com.alihan.uzunoglu.twilio.security.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SignInRequest(
				@JsonProperty("username") String username,
				@JsonProperty("password") String password
) {
}

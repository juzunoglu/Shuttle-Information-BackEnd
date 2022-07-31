package com.alihan.uzunoglu.twilio.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public record SmsRequest(
				@JsonProperty("phoneNumber") @NonNull String phoneNumber,
				@JsonProperty("message") String message,
				@JsonProperty("latitude") @Nullable Float latitude,
				@JsonProperty("longitude") @Nullable Float longitude)
{
	@Override
	public String toString() {
		return "SmsRequest{" +
						"phoneNumber='" + phoneNumber + '\'' +
						", message='" + message + '\'' +
						", latitude=" + latitude +
						", longitude=" + longitude +
						'}';
	}
}

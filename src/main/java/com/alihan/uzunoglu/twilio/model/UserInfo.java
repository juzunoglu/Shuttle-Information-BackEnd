package com.alihan.uzunoglu.twilio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

public record UserInfo(

				@JsonProperty("fullName") @NonNull String fullName,
				@JsonProperty("phoneNumber") @NonNull String phoneNumber,
				@JsonProperty("email") @NonNull String email,
				@JsonProperty("fullAddress") @NonNull String address,
				@JsonProperty("latitude") @NonNull Float latitude,
				@JsonProperty("longitude")@NonNull Float longitude
) {
}

package com.alihan.uzunoglu.twilio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

public record DriverDTO(
				@JsonProperty("name") String name,
				@JsonProperty("age") int age,
				@JsonProperty("phoneNumber") String phoneNumber,
				@JsonProperty("experience") String experience,
				@JsonProperty("email") String email,
				@JsonProperty("driverPhoto") MultipartFile driverPhoto,
				@JsonProperty("carMake") String carMake,
				@JsonProperty("carModel") String carModel,
				@JsonProperty("carPhoto") MultipartFile carPhoto,
				@JsonProperty("carTag") String carTag
) {
	@Override
	public String toString() {
		return "DriverDTO{" +
						"name='" + name + '\'' +
						", age=" + age +
						", phoneNumber='" + phoneNumber + '\'' +
						", experience='" + experience + '\'' +
						", email='" + email + '\'' +
						", driverPhoto=" + driverPhoto +
						", carMake='" + carMake + '\'' +
						", carModel='" + carModel + '\'' +
						", carPhoto=" + carPhoto +
						", carTag='" + carTag + '\'' +
						'}';
	}
}

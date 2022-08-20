package com.alihan.uzunoglu.twilio.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PassengerDTO(
        @JsonProperty("name") String name,
        @JsonProperty("age") int age,
        @JsonProperty("phoneNumber") String phoneNumber,
        @JsonProperty("email") String email,

        @JsonProperty("latitude") Float latitude,
        @JsonProperty("longitude") Float longitude

        ) {
    @Override
    public String toString() {
        return "PassengerDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}

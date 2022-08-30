package com.alihan.uzunoglu.twilio.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record RouteDTO(
        @JsonProperty("latitude") List<Float> latitude,
        @JsonProperty("longitude") List<Float> longitude) {

}

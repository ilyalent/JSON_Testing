package com.json_testing.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@JsonRootName("tickets")
public class Ticket implements Serializable {

    private String origin;

    @JsonProperty("origin_name")
    private String originName;

    private String destination;

    @JsonProperty("destination_name")
    private String destinationName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    @JsonProperty("departure_date")
    private LocalDate departureDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "H:mm")
    @JsonProperty("departure_time")
    private LocalTime departureTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    @JsonProperty("arrival_date")
    private LocalDate arrivalDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "H:mm")
    @JsonProperty("arrival_time")
    private LocalTime arrivalTime;

    private String carrier;
    private int stops;
    private int price;

}

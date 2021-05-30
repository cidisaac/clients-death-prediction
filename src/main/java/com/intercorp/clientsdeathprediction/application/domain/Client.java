package com.intercorp.clientsdeathprediction.application.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDate;

@Value
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Client {
    String firstName;
    String lastName;
    Integer age;
    LocalDate birthDate;
    @With
    LocalDate deathDate;
}

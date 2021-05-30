package com.intercorp.clientsdeathprediction.adapter.database.model;

import com.intercorp.clientsdeathprediction.application.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientModel {

    String firstName;
    String lastName;
    Integer age;
    LocalDate birthDate;
    @With
    LocalDate deathDate;

    public static ClientModel from(Client client) {
        return ClientModel
                .builder()
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .age(client.getAge())
                .birthDate(client.getBirthDate())
                .build();
    }
}

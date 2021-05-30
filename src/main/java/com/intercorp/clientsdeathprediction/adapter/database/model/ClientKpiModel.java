package com.intercorp.clientsdeathprediction.adapter.database.model;

import com.intercorp.clientsdeathprediction.application.domain.ClientKpi;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClientKpiModel {

    Double ageAvg;
    Double standardDeviation;

    public static ClientKpiModel from(ClientKpi clientKpi) {
        return ClientKpiModel
                .builder()
                .ageAvg(clientKpi.getAgeAvg())
                .standardDeviation(clientKpi.getStandardDeviation())
                .build();
    }
}

package com.intercorp.clientsdeathprediction.application.usecase;

import com.intercorp.clientsdeathprediction.application.domain.Client;
import com.intercorp.clientsdeathprediction.application.domain.ClientKpi;
import com.intercorp.clientsdeathprediction.application.ports.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class GetKpiClientsUseCase {

    private static final String URL =  "/kpideclientes";

    private final ClientRepository clientRepository;

    @Autowired
    public GetKpiClientsUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping(URL)
    public ClientKpi getClientsKpi() {
        log.info("Llamado a {}", URL);
        return this.execute();
    }

    private ClientKpi execute() {

        List<Client> clients = clientRepository.getClients();

        return this.buildClientKpi(clients);
    }

    private ClientKpi buildClientKpi(List<Client> clients) {

        var ageList = clients
                .stream()
                .map(Client::getAge)
                .collect(Collectors.toList());

        Double ageAvg = this.getAgeAvg(ageList);

        Double standardDeviation = ageList.isEmpty() ? 0 : this.getStandardDeviation(ageList, ageAvg);

        return ClientKpi
                .builder()
                .ageAvg(ageAvg)
                .standardDeviation(standardDeviation)
                .build();
    }

    private Double getAgeAvg(List<Integer> ages) {
        return ages
                .stream()
                .mapToInt(age -> age)
                .average()
                .orElse(0);
    }

    private Double getStandardDeviation(List<Integer> ages, Double ageAvg) {
        val sum = ages.stream()
                .mapToDouble(age -> Math.pow(age - ageAvg, 2))
                .sum();
        val variance = sum / ages.size();

        return Math.sqrt(variance);
    }
}



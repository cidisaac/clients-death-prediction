package com.intercorp.clientsdeathprediction.application.usecase;

import com.intercorp.clientsdeathprediction.application.domain.Client;
import com.intercorp.clientsdeathprediction.application.ports.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("api/v1")
public class GetListClientUseCase {

    private final ClientRepository clientRepository;
    private static final Double LIFE_EXPECTANCY = 76.52;

    @Autowired
    public GetListClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("listclientes")
    public List<Client> getClientsWithDeathDate() {
        return this.execute();
    }

    private List<Client> execute() {
        val clients = clientRepository.getClients();

        return clients
                .stream()
                .map(client -> client.withDeathDate(calculateDeathDate(client)))
                .collect(Collectors.toList());
    }

    private LocalDate calculateDeathDate(Client client) {
        Double daysRemaining = LIFE_EXPECTANCY * 365;
        return client.getBirthDate().plusDays(daysRemaining.intValue());
    }
}

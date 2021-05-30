package com.intercorp.clientsdeathprediction.application.usecase;

import com.intercorp.clientsdeathprediction.application.domain.Client;
import com.intercorp.clientsdeathprediction.application.ports.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class CreateClientUseCase {

    private final ClientRepository clientRepository;
private static final String URL = "/creacliente";
    @Autowired
    public CreateClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostMapping(URL)
    public String createClient(@RequestBody Client client){
        log.info("Llamado a {} con cliente {} ", URL, client);
        return this.execute(client);
    }

    private String execute(Client client){
        val id = clientRepository.createClient(client);
        return "Se creo el cliente con ID: " + id;
    }
}

package com.intercorp.clientsdeathprediction.application.ports;

import com.intercorp.clientsdeathprediction.application.domain.Client;

import java.util.List;

public interface ClientRepository {
    Integer createClient(Client client);

    List<Client> getClients();
}

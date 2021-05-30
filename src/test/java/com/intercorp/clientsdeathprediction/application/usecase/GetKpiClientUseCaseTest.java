package com.intercorp.clientsdeathprediction.application.usecase;

import com.intercorp.clientsdeathprediction.application.domain.Client;
import com.intercorp.clientsdeathprediction.application.domain.ClientKpi;
import com.intercorp.clientsdeathprediction.application.ports.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Get kpi client use case test")
@ExtendWith(MockitoExtension.class)
public class GetKpiClientUseCaseTest {

    @Test
    @DisplayName("when getKpiClient is called, then ClientKpi is returned")
    void getKpiClientOk() {
        ClientRepository repository = mock(ClientRepository.class);

        List<Client> clientsList = List.of(Client.builder().age(5).build(), Client.builder().age(7).build());

        var expected = ClientKpi.builder().ageAvg(6.0).standardDeviation(1.0).build();
        when(repository.getClients()).thenReturn(clientsList);

        GetKpiClientsUseCase useCase = new GetKpiClientsUseCase(repository);
        var response = useCase.getClientsKpi();

        assertEquals(response, expected);
    }
}

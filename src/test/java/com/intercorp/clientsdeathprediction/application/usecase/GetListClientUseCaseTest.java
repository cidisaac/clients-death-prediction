package com.intercorp.clientsdeathprediction.application.usecase;

import com.intercorp.clientsdeathprediction.application.domain.Client;
import com.intercorp.clientsdeathprediction.application.ports.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Get list of clients use case test")
@ExtendWith(MockitoExtension.class)
public class GetListClientUseCaseTest {

    @Test
    @DisplayName("when getListClientWithDeathDate is called, then return the clients with his death date")
    void getListClientsOk() {
        ClientRepository repository = mock(ClientRepository.class);

        List<Client> clientsList = List.of(Client
                        .builder()
                        .age(5)
                        .birthDate(LocalDate.of(1992, 2, 6))
                        .build(),
                Client
                        .builder()
                        .age(7)
                        .birthDate(LocalDate.of(1992, 2, 7))
                        .build());

        var expected = List.of(Client
                        .builder()
                        .age(5)
                        .birthDate(LocalDate.of(1992, 2, 6))
                        .deathDate(LocalDate.of(2068, 7, 25))
                        .build(),
                Client
                        .builder()
                        .age(7)
                        .birthDate(LocalDate.of(1992, 2, 7))
                        .deathDate(LocalDate.of(2068, 7, 26))
                        .build()
        );
        when(repository.getClients()).thenReturn(clientsList);

        GetListClientUseCase useCase = new GetListClientUseCase(repository);
        var response = useCase.getClientsWithDeathDate();

        assertEquals(response, expected);
    }
}

package com.intercorp.clientsdeathprediction.application.usecase;

import com.intercorp.clientsdeathprediction.application.domain.Client;
import com.intercorp.clientsdeathprediction.application.ports.ClientRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Create client use case test")
@ExtendWith(MockitoExtension.class)
public class CreateClientUseCaseTest {

    @Test
    @DisplayName("when createClient is called, then insert method is called")
    void createClientOk() {
        ClientRepository repository = mock(ClientRepository.class);
        EasyRandom generator = new EasyRandom();

        CreateClientUseCase useCase = new CreateClientUseCase(repository);
        useCase.createClient(generator.nextObject(Client.class));

        verify(repository, times(1)).createClient(any());
    }

}

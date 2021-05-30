package com.intercorp.clientsdeathprediction.adapter.database;

import com.intercorp.clientsdeathprediction.adapter.database.model.ClientModel;
import com.intercorp.clientsdeathprediction.application.domain.Client;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Client adapter test")
@ExtendWith(MockitoExtension.class)
public class ClientAdapterTest {

    private static final SqlParser sqlParser = mock(SqlParser.class);
    private static final Integer ID_TO_RETURN = 1;
    private static final String SQL = "SELECT 1;";

    @BeforeAll
    static void setSqlParser() {
        when(sqlParser.parse(anyString())).thenReturn(SQL);
    }

    @Test
    @DisplayName("when createClient is called, then we get de created id")
    void createClientOk() {

        EasyRandom generator = new EasyRandom();

        JdbcTemplateImplementation jdbcTemplateMock = Mockito.mock(JdbcTemplateImplementation.class);
        ClientAdapter clientAdapter = new ClientAdapter(jdbcTemplateMock, sqlParser);

        var expectedId = ID_TO_RETURN;

        when(jdbcTemplateMock.insert(any(), any())).thenReturn(ID_TO_RETURN);

        var response = clientAdapter.createClient(generator.nextObject(Client.class));
        assertEquals(expectedId, response);
    }

    @Test
    @DisplayName("when getClients is called, the return a list of clients")
    void getClientsOk() {

        EasyRandom generator = new EasyRandom();

        JdbcTemplateImplementation jdbcTemplateMock = Mockito.mock(JdbcTemplateImplementation.class);
        ClientAdapter clientAdapter = new ClientAdapter(jdbcTemplateMock, sqlParser);

        var client1 = generator.nextObject(ClientModel.class);
        var client2 = generator.nextObject(ClientModel.class);

        var expectedListOfClients = List.of(
                Client
                        .builder()
                        .firstName(client1.getFirstName())
                        .lastName(client1.getLastName())
                        .age(client1.getAge())
                        .birthDate(client1.getBirthDate())
                .build(),
                Client
                        .builder()
                        .firstName(client2.getFirstName())
                        .lastName(client2.getLastName())
                        .age(client2.getAge())
                        .birthDate(client2.getBirthDate())
                        .build()
        );

        when(jdbcTemplateMock.getAllClients(any(), any())).thenReturn(List.of(client1, client2));

        var response = clientAdapter.getClients();

        assertEquals(expectedListOfClients, response);
    }
}

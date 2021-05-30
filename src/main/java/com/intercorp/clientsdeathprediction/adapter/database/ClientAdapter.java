package com.intercorp.clientsdeathprediction.adapter.database;

import com.intercorp.clientsdeathprediction.adapter.database.JdbcTemplateImplementation;
import com.intercorp.clientsdeathprediction.adapter.database.SqlParser;
import com.intercorp.clientsdeathprediction.adapter.database.model.ClientModel;
import com.intercorp.clientsdeathprediction.application.domain.Client;
import com.intercorp.clientsdeathprediction.application.ports.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class ClientAdapter implements ClientRepository {

    private final JdbcTemplateImplementation jdbcTemplate;
    private final String insertClientSql;
    private final String getAllClientsSql;
    private static final String CLIENT_INSERT_SQL = "queries/insert.sql";
    private static final String GET_ALL_CLIENTS_SQL = "queries/getAllClients.sql";

    public ClientAdapter(JdbcTemplateImplementation jdbcTemplate, SqlParser sqlParser) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertClientSql = sqlParser.parse(CLIENT_INSERT_SQL);
        this.getAllClientsSql = sqlParser.parse(GET_ALL_CLIENTS_SQL);
    }

    @Override
    public Integer createClient(Client client) {

        ClientModel clientToInsert = ClientModel.from(client);
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(clientToInsert);

        log.info("Se procede a ejecutar el insert del cliente {}", client);
        return jdbcTemplate.insert(insertClientSql, parameters);
    }

    @Override
    public List<Client> getClients() {

        log.info("Se procede a ejecutar el getAllClients");
        var clientModelList = jdbcTemplate.getAllClients(getAllClientsSql, ClientModel.class);
        return clientModelList
                .stream()
                .map(client ->
                        Client.builder()
                                .firstName(client.getFirstName())
                                .lastName(client.getLastName())
                                .age(client.getAge())
                                .birthDate(client.getBirthDate())
                                .build())
                .collect(Collectors.toList());
    }
}

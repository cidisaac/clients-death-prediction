package com.intercorp.clientsdeathprediction.adapter.database;

import com.intercorp.clientsdeathprediction.adapter.database.model.ClientModel;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.catchThrowable;


@DisplayName("Jdbc Template Test")
@ExtendWith(MockitoExtension.class)
public class JdbcTemplateImplementationTest {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate = mock(NamedParameterJdbcTemplate.class);
    private static final String SQL = "SELECT 1;";
    private static final SqlParameterSource SOURCE = new MapSqlParameterSource("ParamName", 1);

    @Test
    @DisplayName("when insert ok, then return inserted Id")
    void insertException() {
        JdbcTemplateImplementation template = new JdbcTemplateImplementation(namedParameterJdbcTemplate);
        when(namedParameterJdbcTemplate.update(eq(SQL), eq(SOURCE), any(KeyHolder.class), any())).thenReturn(0);
        Throwable thrown = catchThrowable(() -> template.insert(SQL, SOURCE));
        assertNotNull(thrown);
    }

    @Test
    @DisplayName("when getAllClients is ok, then return list of clients")
    void getAllClientsOk() {
        JdbcTemplateImplementation template = new JdbcTemplateImplementation(namedParameterJdbcTemplate);

        EasyRandom generator = new EasyRandom();

        List<ClientModel> listOfClients = List.of(generator.nextObject(ClientModel.class), generator.nextObject(ClientModel.class));

        when(namedParameterJdbcTemplate.query(eq(SQL), isA(BeanPropertyRowMapper.class))).thenReturn(listOfClients);

        var response = template.getAllClients(SQL, ClientModel.class);

        assertEquals(response, listOfClients);
    }
}

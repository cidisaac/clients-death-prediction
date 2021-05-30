package com.intercorp.clientsdeathprediction.adapter.database;

import com.intercorp.clientsdeathprediction.adapter.database.exception.DatabaseException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
public class JdbcTemplateImplementation {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcTemplateImplementation(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @SneakyThrows
    public int insert(String sql, SqlParameterSource parameters) {
        KeyHolder holder = new GeneratedKeyHolder();

        int affectedRows = jdbcTemplate.update(
                sql,
                parameters,
                holder,
                new String[]{"id"}
        );

        int id = Optional
                .ofNullable(holder.getKey())
                .map(Number::intValue)
                .orElseThrow(() -> new DatabaseException("Error al realizar el insert en la DB", new RuntimeException()));

        log.info("Rows affected: {}, ID: {}", affectedRows, id);
        return id;
    }

    public <T> List<T> getAllClients(String sql, Class<T> clazz) {
        val clients =  jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(clazz));
        log.info("Se obtuvieron los clientes {}", clients);
        return clients;
    }
}

package com.intercorp.clientsdeathprediction.adapter.database;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class SqlParser {

    @SneakyThrows
    public String parse(final String sqlPath) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(sqlPath);
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8.name())) {
            textBuilder.append(FileCopyUtils.copyToString(reader));
            return textBuilder.toString();
        } catch (IOException ex) {
            log.error("Error al leer el archivo sql", ex);
            throw new Exception("Sql error");
        }
    }

}



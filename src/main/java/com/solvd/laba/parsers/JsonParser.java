package com.solvd.laba.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.laba.domain.Company;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JsonParser {
    private static final Logger LOGGER = LogManager.getLogger(JsonParser.class);
    public static void main(String[] args) {
        File file = new File("src/main/resources/company.json");

        ObjectMapper mapper = new ObjectMapper();
        try {
            Company company = mapper.readValue(file, Company.class);
            LOGGER.trace(company);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

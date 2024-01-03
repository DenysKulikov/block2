package com.solvd.laba.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.laba.domain.Company;

import java.io.File;
import java.io.IOException;

public class JsonParser {
    public static void main(String[] args) {
        File file = new File("src/main/resources/company.json");

        ObjectMapper mapper = new ObjectMapper();
        try {
            Company company = mapper.readValue(file, Company.class);
            System.out.println(company);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.solvd.laba.patterns.factory;

import com.solvd.laba.domain.Company;
import com.solvd.laba.service.CompanyService;
import com.solvd.laba.service.impl.CompanyServiceImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class FactoryMain {
    public static final String REPOSITORY_IMPL_TYPE;
    public static final String DB_TYPE;

    static {
        try(FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties")) {
            Properties properties = new Properties();
            properties.load(fileInputStream);

            REPOSITORY_IMPL_TYPE = properties.getProperty("repositoryImplType");
            DB_TYPE = properties.getProperty("dbType");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        CompanyService companyService = new CompanyServiceImpl(DB_TYPE, REPOSITORY_IMPL_TYPE);

        Company company = new Company();
        company.setName("IntegralBud");
        try {
            companyService.create(company);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        companyService.delete(company.getId());
    }
}

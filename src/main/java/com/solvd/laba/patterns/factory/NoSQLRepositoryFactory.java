package com.solvd.laba.patterns.factory;

import com.solvd.laba.persistence.noSQL.CompanyMongoDBRepositoryImpl;
import com.solvd.laba.persistence.repositories.CompanyRepository;

public class NoSQLRepositoryFactory implements RepositoryFactory {
    @Override
    public CompanyRepository createCompanyRepository(String type) {
        CompanyRepository result;
        switch (type) {
            case "MONGODB":
                result = new CompanyMongoDBRepositoryImpl();
                break;
            default:
                throw new RuntimeException(String.format("Unable to create an object related to the '%s' type", type));
        }
        return result;
    }
}

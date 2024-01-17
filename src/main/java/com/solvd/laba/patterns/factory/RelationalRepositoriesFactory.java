package com.solvd.laba.patterns.factory;

import com.solvd.laba.persistence.impl.CompanyRepositoryImpl;
import com.solvd.laba.persistence.mybatisImpl.CompanyRepositoryMybatisIpl;
import com.solvd.laba.persistence.repositories.CompanyRepository;

public class RelationalRepositoriesFactory implements RepositoryFactory {
    @Override
    public CompanyRepository createCompanyRepository(String type) {
        CompanyRepository result;
        switch (type) {
            case "JDBC":
                result = new CompanyRepositoryImpl();
                break;
            case "MYBATIS":
                result = new CompanyRepositoryMybatisIpl();
                break;
            default:
                throw new RuntimeException(String.format("Unable to create an object related to the '%s' type", type));
        }
        return result;
    }
}

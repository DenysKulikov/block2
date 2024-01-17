package com.solvd.laba.patterns.factory;

import com.solvd.laba.persistence.repositories.CompanyRepository;

public interface RepositoryFactory {
    CompanyRepository createCompanyRepository(String type);
}

package com.solvd.laba.service.impl;

import com.solvd.laba.domain.Company;
import com.solvd.laba.persistence.repositories.CompanyRepository;
import com.solvd.laba.service.CompanyService;

import java.sql.SQLException;
import java.util.List;

public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company create(Company company) throws SQLException {
        company.setId(null);
        return companyRepository.create(company);
    }

    @Override
    public void delete(Long companyId) {
        companyRepository.delete(companyId);
    }

    @Override
    public List<Company> retrieveAll() {
        return companyRepository.findAll();
    }

    @Override
    public Long getCompanyId(Company company) throws SQLException {
        return companyRepository.getCompanyId(company);
    }
}

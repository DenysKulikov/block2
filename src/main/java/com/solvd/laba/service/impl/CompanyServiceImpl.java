package com.solvd.laba.service.impl;

import com.solvd.laba.domain.Company;
import com.solvd.laba.persistence.repositories.CompanyRepository;
import com.solvd.laba.service.CompanyService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public void create(Company company) throws SQLException {
        company.setId(null);
        companyRepository.create(company);
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
    public Optional<Company> findById(Long companyId) throws SQLException {
        return companyRepository.findById(companyId);
    }
}

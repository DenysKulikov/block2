package com.solvd.laba.persistence.noSQL;

import com.solvd.laba.domain.Company;
import com.solvd.laba.persistence.repositories.CompanyRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CompanyMongoDBRepositoryImpl implements CompanyRepository {
    @Override
    public void create(Company company) throws SQLException {

    }

    @Override
    public void delete(Long companyId) {

    }

    @Override
    public void update(Company company, Long companyId) {

    }

    @Override
    public List<Company> findAll() {
        return null;
    }

    @Override
    public Optional<Company> findById(Long companyId) throws SQLException {
        return Optional.empty();
    }
}

package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.Company;

import java.sql.SQLException;
import java.util.List;

public interface CompanyRepository {
    Company create(Company company) throws SQLException;
    void delete(Long companyId);
    List<Company> findAll();
    Long getCompanyId(Company company) throws SQLException;
}

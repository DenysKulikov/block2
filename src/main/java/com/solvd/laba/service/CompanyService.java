package com.solvd.laba.service;

import com.solvd.laba.domain.Company;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Company create(Company company) throws SQLException;
    void delete(Long companyId);
    List<Company> retrieveAll();
    Optional<Company> findById(Long companyId) throws SQLException;
}

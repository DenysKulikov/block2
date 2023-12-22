package com.solvd.laba.service;

import com.solvd.laba.domain.Company;
import com.solvd.laba.domain.Salary;

import java.sql.SQLException;
import java.util.List;

public interface CompanyService {
    Company create(Company company) throws SQLException;
    List<Company> retrieveAll();
    Long getCompanyId(Company company) throws SQLException;
}

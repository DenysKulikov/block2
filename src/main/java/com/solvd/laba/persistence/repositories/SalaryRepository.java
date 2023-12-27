package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.Company;
import com.solvd.laba.domain.Salary;

import java.sql.SQLException;
import java.util.Optional;

public interface SalaryRepository {
    void create(Salary salary);
    void delete(Long salaryId);
    Optional<Salary> findById(Long salaryId) throws SQLException;
}

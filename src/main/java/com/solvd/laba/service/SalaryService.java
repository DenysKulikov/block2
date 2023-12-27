package com.solvd.laba.service;

import com.solvd.laba.domain.Salary;

import java.sql.SQLException;
import java.util.Optional;

public interface SalaryService {
    void create(Salary salary);
    void delete(Long salaryId);
    Optional<Salary> findById(Long salaryId) throws SQLException;
}

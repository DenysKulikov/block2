package com.solvd.laba.service;

import com.solvd.laba.domain.Salary;

import java.sql.SQLException;

public interface SalaryService {
    void create(Salary salary);
    void delete(Long salaryId);
    Long getSalaryId(Salary salary) throws SQLException;
}

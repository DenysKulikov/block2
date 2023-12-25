package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.Salary;

import java.sql.SQLException;

public interface SalaryRepository {
    void create(Salary salary);
    void delete(Long salaryId);
    Long getSalaryId(Salary salary) throws SQLException;
}

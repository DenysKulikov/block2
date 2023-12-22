package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.Salary;

import java.sql.SQLException;

public interface SalaryRepository {
    Salary create(Salary salary);
    Long getSalaryId(Salary salary) throws SQLException;
}

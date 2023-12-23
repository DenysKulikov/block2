package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.Salary;
import com.solvd.laba.domain.enums.Position;

import java.sql.SQLException;

public interface SalaryRepository {
    Salary create(Salary salary);
    void delete(Long salaryId);
    Long getSalaryId(Salary salary) throws SQLException;
}

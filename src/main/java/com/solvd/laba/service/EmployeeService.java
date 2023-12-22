package com.solvd.laba.service;

import com.solvd.laba.domain.Company;
import com.solvd.laba.domain.Employee;
import com.solvd.laba.domain.Salary;
import com.solvd.laba.domain.enums.Position;

import java.sql.SQLException;

public interface EmployeeService {
    Employee create(Employee employee, Company company, Salary salary, Position position) throws SQLException;
}

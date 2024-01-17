package com.solvd.laba.patterns.facade;

import com.solvd.laba.domain.Employee;
import com.solvd.laba.persistence.repositories.EmployeeRepository;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Override
    public void create(Employee employee) {

    }

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
}

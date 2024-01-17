package com.solvd.laba.service.impl;


import com.solvd.laba.domain.Employee;

import com.solvd.laba.persistence.repositories.EmployeeRepository;
import com.solvd.laba.service.EmployeeService;

import java.sql.SQLException;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void create(Employee employee, Long companyId, Long salaryId, String positionName) throws SQLException {
        employee.setId(null);
        employeeRepository.create(employee, companyId, salaryId, positionName);
    }

    @Override
    public void addEmployeeToBuilding(Long employeeId, Long buildingId) {
        employeeRepository.addEmployeeToBuilding(employeeId, buildingId);
    }

    @Override
    public void deleteEmployeeFromBuilding(Long employeeId, Long buildingId) {
        employeeRepository.deleteEmployeeFromBuilding(employeeId, buildingId);
    }

    @Override
    public void delete(Long employeeId) {
        employeeRepository.delete(employeeId);
    }
}

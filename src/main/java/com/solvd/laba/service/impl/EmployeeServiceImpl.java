package com.solvd.laba.service.impl;

import com.solvd.laba.domain.Company;
import com.solvd.laba.domain.Employee;
import com.solvd.laba.domain.Salary;
import com.solvd.laba.domain.enums.Position;
import com.solvd.laba.persistence.repositories.EmployeeRepository;
import com.solvd.laba.service.CompanyService;
import com.solvd.laba.service.EmployeeService;
import com.solvd.laba.service.PositionService;
import com.solvd.laba.service.SalaryService;

import java.sql.SQLException;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PositionService positionService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PositionService positionService) {
        this.employeeRepository = employeeRepository;
        this.positionService = positionService;
    }

    @Override
    public void create(Employee employee, Long companyId, Long salaryId, Position position) throws SQLException {
        employee.setId(null);

        positionService.create(position);
        employee.setHasCar(position.hasCar());
        employeeRepository.create(employee, companyId, salaryId, position);
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

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
    private final CompanyService companyService;
    private final SalaryService salaryService;
    private final PositionService positionService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyService companyService,
                               SalaryService salaryService, PositionService positionService) {
        this.employeeRepository = employeeRepository;
        this.companyService = companyService;
        this.salaryService = salaryService;
        this.positionService = positionService;
    }

    @Override
    public Employee create(Employee employee, Company company, Salary salary, Position position) throws SQLException {
        employee.setId(null);

        companyService.create(company);
        Long companyId = companyService.getCompanyId(company);

        salaryService.create(salary);
        Long salaryId = salaryService.getSalaryId(salary);

        positionService.create(position);
        employee.setHasCar(position.hasCar());
        return employeeRepository.create(employee, companyId, salaryId, position);
    }
}

package com.solvd.laba.patterns.facade;


import com.solvd.laba.domain.Company;
import com.solvd.laba.domain.Employee;

public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;
    private EmployeeService employeeService;

    @Override
    public void create(Company company) {
        companyRepository.create(company);
        for (Employee employee : company.getEmployees()) {
            employeeService.create(employee);
        }
    }

    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
}

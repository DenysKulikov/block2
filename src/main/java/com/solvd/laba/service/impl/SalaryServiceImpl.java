package com.solvd.laba.service.impl;

import com.solvd.laba.domain.Salary;
import com.solvd.laba.persistence.repositories.SalaryRepository;
import com.solvd.laba.service.SalaryService;

import java.sql.SQLException;

public class SalaryServiceImpl implements SalaryService {
    private final SalaryRepository salaryRepository;

    public SalaryServiceImpl(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    @Override
    public Salary create(Salary salary) {
        salary.setId(null);
        return salaryRepository.create(salary);
    }

    @Override
    public void delete(Long salaryId) {
        salaryRepository.delete(salaryId);
    }

    @Override
    public Long getSalaryId(Salary salary) throws SQLException {
        return salaryRepository.getSalaryId(salary);
    }
}
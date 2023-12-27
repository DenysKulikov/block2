package com.solvd.laba.service.impl;

import com.solvd.laba.domain.Salary;
import com.solvd.laba.persistence.repositories.SalaryRepository;
import com.solvd.laba.service.SalaryService;

import java.sql.SQLException;
import java.util.Optional;

public class SalaryServiceImpl implements SalaryService {
    private final SalaryRepository salaryRepository;

    public SalaryServiceImpl(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    @Override
    public void create(Salary salary) {
        salary.setId(null);
        salaryRepository.create(salary);
    }

    @Override
    public void delete(Long salaryId) {
        salaryRepository.delete(salaryId);
    }

    @Override
    public Optional<Salary> findById(Long salaryId) throws SQLException {
        return salaryRepository.findById(salaryId);
    }
}

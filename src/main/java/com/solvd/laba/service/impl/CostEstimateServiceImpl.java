package com.solvd.laba.service.impl;

import com.solvd.laba.domain.CostEstimate;
import com.solvd.laba.persistence.repositories.CostEstimateRepository;
import com.solvd.laba.service.CostEstimateService;

import java.sql.SQLException;

public class CostEstimateServiceImpl implements CostEstimateService {
    private final CostEstimateRepository costEstimateRepository;

    public CostEstimateServiceImpl(CostEstimateRepository costEstimateRepository) {
        this.costEstimateRepository = costEstimateRepository;
    }

    @Override
    public CostEstimate create(CostEstimate costEstimate, Long buildingId) throws SQLException {
        return costEstimateRepository.create(costEstimate, buildingId);
    }

    @Override
    public void delete(Long costEstimateId) {
        costEstimateRepository.delete(costEstimateId);
    }

    @Override
    public CostEstimate findById(Long companyId) throws SQLException {
        return costEstimateRepository.findById(companyId);
    }
}

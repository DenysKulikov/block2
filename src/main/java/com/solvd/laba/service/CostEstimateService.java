package com.solvd.laba.service;

import com.solvd.laba.domain.Company;
import com.solvd.laba.domain.CostEstimate;

import java.sql.SQLException;
import java.util.Optional;

public interface CostEstimateService {
    void create(CostEstimate costEstimate, Long buildingId) throws SQLException;
    void delete(Long costEstimateId);
    CostEstimate findById(Long costEstimateId) throws SQLException;
}

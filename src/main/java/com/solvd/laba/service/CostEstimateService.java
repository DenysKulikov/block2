package com.solvd.laba.service;

import com.solvd.laba.domain.CostEstimate;

import java.sql.SQLException;

public interface CostEstimateService {
    CostEstimate create(CostEstimate costEstimate, Long buildingId) throws SQLException;
    void delete(Long costEstimateId);
    Long getCostEstimateId(CostEstimate costEstimate) throws SQLException;
}

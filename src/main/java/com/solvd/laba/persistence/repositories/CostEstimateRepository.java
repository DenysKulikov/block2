package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.BuildingApproval;
import com.solvd.laba.domain.CostEstimate;

import java.sql.SQLException;

public interface CostEstimateRepository {
    CostEstimate create(CostEstimate costEstimate, Long buildingId) throws SQLException;
    void delete(Long costEstimateId);
    Long getCostEstimateId(CostEstimate costEstimate) throws SQLException;
}

package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.Company;
import com.solvd.laba.domain.CostEstimate;

import java.sql.SQLException;

public interface CostEstimateRepository {
    void create(CostEstimate costEstimate, Long buildingId) throws SQLException;
    void delete(Long costEstimateId);
    CostEstimate findById(Long costEstimateId) throws SQLException;
}

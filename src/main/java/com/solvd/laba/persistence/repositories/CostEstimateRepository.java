package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.CostEstimate;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

public interface CostEstimateRepository {
    void create(@Param("costEstimate") CostEstimate costEstimate, @Param("buildingId") Long buildingId) throws SQLException;
    void delete(Long costEstimateId);
    CostEstimate findById(Long costEstimateId) throws SQLException;
}

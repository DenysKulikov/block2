package com.solvd.laba.service;

import com.solvd.laba.domain.BuildingApproval;

import java.sql.SQLException;

public interface BuildingApprovalService {
    BuildingApproval create(BuildingApproval buildingApproval, Long buildingId) throws SQLException;
    void delete(Long buildingApprovalId);
    BuildingApproval findById(Long buildingApprovalId) throws SQLException;
}

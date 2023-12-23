package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.Building;
import com.solvd.laba.domain.BuildingApproval;

import java.sql.SQLException;

public interface BuildingApprovalRepository {
    BuildingApproval create(BuildingApproval buildingApproval, Long buildingId) throws SQLException;
    void delete(Long buildingApprovalId);
    Long getBuildingApprovalId(BuildingApproval buildingApproval) throws SQLException;
}

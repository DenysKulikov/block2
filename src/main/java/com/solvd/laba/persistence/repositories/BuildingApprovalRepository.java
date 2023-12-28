package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.BuildingApproval;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

public interface BuildingApprovalRepository {
    void create(@Param("buildingApproval") BuildingApproval buildingApproval, @Param("buildingId") Long buildingId) throws SQLException;
    void delete(Long buildingApprovalId);
    BuildingApproval findById(Long buildingApprovalId) throws SQLException;
}

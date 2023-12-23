package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.Building;

import java.sql.SQLException;

public interface BuildingRepository {
    Building create(Building building, Long companyId) throws SQLException;
    void delete(Long buildingId);
    Long getBuildingId(Building building) throws SQLException;
}

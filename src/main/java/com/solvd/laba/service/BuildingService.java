package com.solvd.laba.service;

import com.solvd.laba.domain.Building;

import java.sql.SQLException;
import java.util.Optional;

public interface BuildingService {
    Building create(Building building, Long companyId) throws SQLException;
    void delete(Long buildingId);
    Optional<Building> findById(Long buildingId) throws SQLException;
}

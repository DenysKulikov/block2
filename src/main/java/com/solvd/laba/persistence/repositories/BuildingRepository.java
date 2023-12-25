package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.Building;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.Optional;

public interface BuildingRepository {
    Building create(@Param("building") Building building, @Param("companyId") Long companyId) throws SQLException;
    void delete(Long buildingId);

    Optional<Building> findById(Long buildingId) throws SQLException;
}

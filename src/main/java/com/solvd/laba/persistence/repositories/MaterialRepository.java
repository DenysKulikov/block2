package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.Material;

import java.sql.SQLException;

public interface MaterialRepository {
    Material create(Material material) throws SQLException;
    void delete(Long materialId);
    Long getMaterialId(Material material) throws SQLException;
    void addMaterialToBuilding(Long materialId, Long buildingId);
    void deleteMaterialFromBuilding(Long materialId, Long buildingId);
}

package com.solvd.laba.service;

import com.solvd.laba.domain.Material;

import java.sql.SQLException;

public interface MaterialService {
    void create(Material material) throws SQLException;
    void delete(Long materialId);
    Long getMaterialId(Material material) throws SQLException;
    void addMaterialToBuilding(Long materialId, Long buildingId);
    void deleteMaterialFromBuilding(Long materialId, Long buildingId);
}

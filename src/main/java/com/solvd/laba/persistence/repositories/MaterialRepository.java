package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.Material;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

public interface MaterialRepository {
    void create(Material material) throws SQLException;
    void delete(Long materialId);
    Long getMaterialId(Material material) throws SQLException;
    void addMaterialToBuilding(@Param("materialId") Long materialId, @Param("buildingId") Long buildingId);
    void deleteMaterialFromBuilding(@Param("materialId") Long materialId, @Param("buildingId") Long buildingId);
}

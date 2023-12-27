package com.solvd.laba.service.impl;

import com.solvd.laba.domain.Material;
import com.solvd.laba.persistence.repositories.MaterialRepository;
import com.solvd.laba.service.MaterialService;

import java.sql.SQLException;

public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;

    public MaterialServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public void create(Material material) throws SQLException {
        materialRepository.create(material);
    }

    @Override
    public void delete(Long materialId) {
        materialRepository.delete(materialId);
    }

    @Override
    public Long getMaterialId(Material material) throws SQLException {
        return materialRepository.getMaterialId(material);
    }

    @Override
    public void addMaterialToBuilding(Long materialId, Long buildingId) {
        materialRepository.addMaterialToBuilding(materialId, buildingId);
    }

    @Override
    public void deleteMaterialFromBuilding(Long materialId, Long buildingId) {
        materialRepository.deleteMaterialFromBuilding(materialId, buildingId);
    }
}

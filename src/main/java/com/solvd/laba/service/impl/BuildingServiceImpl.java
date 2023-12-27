package com.solvd.laba.service.impl;

import com.solvd.laba.domain.Building;
import com.solvd.laba.persistence.repositories.BuildingRepository;
import com.solvd.laba.service.BuildingService;

import java.sql.SQLException;
import java.util.Optional;

public class BuildingServiceImpl implements BuildingService {
    private final BuildingRepository buildingRepository;

    public BuildingServiceImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @Override
    public void create(Building building, Long companyId) throws SQLException {
        buildingRepository.create(building, companyId);
    }

    @Override
    public void delete(Long buildingId) {
        buildingRepository.delete(buildingId);
    }

    @Override
    public Optional<Building> findById(Long buildingId) throws SQLException {
        return buildingRepository.findById(buildingId);
    }
}

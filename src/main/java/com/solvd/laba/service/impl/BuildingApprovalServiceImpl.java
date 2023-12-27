package com.solvd.laba.service.impl;

import com.solvd.laba.domain.BuildingApproval;
import com.solvd.laba.persistence.repositories.BuildingApprovalRepository;
import com.solvd.laba.service.BuildingApprovalService;

import java.sql.SQLException;

public class BuildingApprovalServiceImpl implements BuildingApprovalService {
    private final BuildingApprovalRepository buildingApprovalRepository;

    public BuildingApprovalServiceImpl(BuildingApprovalRepository buildingApprovalRepository) {
        this.buildingApprovalRepository = buildingApprovalRepository;
    }

    @Override
    public void create(BuildingApproval buildingApproval, Long buildingId) throws SQLException {
        buildingApprovalRepository.create(buildingApproval, buildingId);
    }

    @Override
    public void delete(Long buildingApprovalId) {
        buildingApprovalRepository.delete(buildingApprovalId);
    }

    @Override
    public BuildingApproval findById(Long buildingApprovalId) throws SQLException {
        return buildingApprovalRepository.findById(buildingApprovalId);
    }
}

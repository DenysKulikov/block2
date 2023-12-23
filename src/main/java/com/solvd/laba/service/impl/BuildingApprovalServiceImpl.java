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
    public BuildingApproval create(BuildingApproval buildingApproval, Long buildingId) throws SQLException {
        return buildingApprovalRepository.create(buildingApproval, buildingId);
    }

    @Override
    public void delete(Long buildingApprovalId) {
        buildingApprovalRepository.delete(buildingApprovalId);
    }

    @Override
    public Long getBuildingApprovalId(BuildingApproval buildingApproval) throws SQLException {
        return buildingApprovalRepository.getBuildingApprovalId(buildingApproval);
    }
}

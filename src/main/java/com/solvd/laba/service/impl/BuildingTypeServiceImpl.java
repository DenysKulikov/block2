package com.solvd.laba.service.impl;

import com.solvd.laba.domain.enums.BuildingType;
import com.solvd.laba.persistence.repositories.BuildingTypeRepository;
import com.solvd.laba.service.BuildingTypeService;

public class BuildingTypeServiceImpl implements BuildingTypeService {
    private final BuildingTypeRepository buildingTypeRepository;

    public BuildingTypeServiceImpl(BuildingTypeRepository buildingTypeRepository) {
        this.buildingTypeRepository = buildingTypeRepository;
    }

    @Override
    public BuildingType crete(BuildingType buildingType) {
        return buildingTypeRepository.crete(buildingType);
    }

    @Override
    public void delete(BuildingType buildingType) {
        buildingTypeRepository.delete(buildingType);
    }
}

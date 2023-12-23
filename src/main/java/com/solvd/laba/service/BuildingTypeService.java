package com.solvd.laba.service;

import com.solvd.laba.domain.enums.BuildingType;

public interface BuildingTypeService {
    BuildingType crete(BuildingType buildingType);
    void delete(BuildingType buildingType);
}

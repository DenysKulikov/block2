package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.enums.BuildingType;

public interface BuildingTypeRepository {
    void crete(BuildingType buildingType);
    void delete(BuildingType buildingType);
}

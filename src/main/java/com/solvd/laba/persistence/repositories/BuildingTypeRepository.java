package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.BuildingType;

public interface BuildingTypeRepository {
    void create(BuildingType buildingType);
    void delete(BuildingType buildingType);
}

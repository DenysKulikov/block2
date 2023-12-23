package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.enums.BuildingType;
import com.solvd.laba.domain.enums.Position;

public interface BuildingTypeRepository {
    BuildingType crete(BuildingType buildingType);
    void delete(BuildingType buildingType);
}

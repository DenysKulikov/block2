package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.enums.BuildingType;
import com.solvd.laba.domain.enums.MaterialType;

public interface MaterialTypeRepository {
    MaterialType crete(MaterialType materialType);
    void delete(MaterialType materialType);
}

package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.enums.MaterialType;

public interface MaterialTypeRepository {
    void crete(MaterialType materialType);
    void delete(MaterialType materialType);
}

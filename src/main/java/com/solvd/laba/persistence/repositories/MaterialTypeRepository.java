package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.MaterialType;

public interface MaterialTypeRepository {
    void create(MaterialType materialType);
    void delete(MaterialType materialType);
}

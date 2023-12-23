package com.solvd.laba.service;

import com.solvd.laba.domain.enums.MaterialType;

public interface MaterialTypeService {
    MaterialType crete(MaterialType materialType);
    void delete(MaterialType materialType);
}

package com.solvd.laba.service.impl;

import com.solvd.laba.domain.enums.MaterialType;
import com.solvd.laba.persistence.repositories.MaterialTypeRepository;
import com.solvd.laba.service.MaterialTypeService;

import java.sql.SQLException;

public class MaterialTypeServiceImpl implements MaterialTypeService {
    private final MaterialTypeRepository materialTypeRepository;

    public MaterialTypeServiceImpl(MaterialTypeRepository materialTypeRepository) {
        this.materialTypeRepository = materialTypeRepository;
    }

    @Override
    public MaterialType crete(MaterialType materialType) {
        return materialTypeRepository.crete(materialType);
    }

    @Override
    public void delete(MaterialType materialType) {
        materialTypeRepository.delete(materialType);
    }
}

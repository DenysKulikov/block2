package com.solvd.laba.service.impl;

import com.solvd.laba.domain.MaterialType;
import com.solvd.laba.persistence.repositories.MaterialTypeRepository;
import com.solvd.laba.service.MaterialTypeService;

public class MaterialTypeServiceImpl implements MaterialTypeService {
    private final MaterialTypeRepository materialTypeRepository;

    public MaterialTypeServiceImpl(MaterialTypeRepository materialTypeRepository) {
        this.materialTypeRepository = materialTypeRepository;
    }

    @Override
    public void crete(MaterialType materialType) {
        materialTypeRepository.create(materialType);
    }

    @Override
    public void delete(MaterialType materialType) {
        materialTypeRepository.delete(materialType);
    }
}

package com.solvd.laba.service.impl;

import com.solvd.laba.domain.enums.Position;
import com.solvd.laba.persistence.repositories.PositionRepository;
import com.solvd.laba.service.PositionService;

public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;

    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public Position create(Position position) {
        return positionRepository.crete(position);
    }
}

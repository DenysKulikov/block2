package com.solvd.laba.service;

import com.solvd.laba.domain.enums.Position;

public interface PositionService {
    Position create(Position position);
    void delete(Position position);
}

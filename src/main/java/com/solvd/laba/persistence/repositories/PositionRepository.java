package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.Position;

public interface PositionRepository {
    void create(Position position);
    void delete(Position position);
}

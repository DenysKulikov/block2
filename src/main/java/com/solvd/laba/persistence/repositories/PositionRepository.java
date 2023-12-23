package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.enums.Position;

public interface PositionRepository {
    Position crete(Position position);
    void delete(Position position);
}

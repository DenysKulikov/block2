package com.solvd.laba.domain.enums;

import java.math.BigDecimal;

public enum BuildingType {
    RESIDENTIAL(BigDecimal.valueOf(120_000.00)),
    COMMERCIAL(BigDecimal.valueOf(180_000.00)),
    INDUSTRIAL(BigDecimal.valueOf(250_000.00));
    private final BigDecimal baseCost;

    BuildingType(BigDecimal baseCost) {
        this.baseCost = baseCost;
    }

    public BigDecimal getBaseCost() {
        return baseCost;
    }
}

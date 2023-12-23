package com.solvd.laba.domain.enums;

public enum Position {
    BUILDER(false),
    DRIVER(true);
    private final boolean hasCar;

    Position(boolean hasCar) {
        this.hasCar = hasCar;
    }

    public boolean hasCar() {
        return hasCar;
    }
}

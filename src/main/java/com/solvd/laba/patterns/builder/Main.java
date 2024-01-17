package com.solvd.laba.patterns.builder;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Smith")
                .age(19)
                .salary(BigDecimal.valueOf(1000))
                .position("driver")
                .build();
    }
}

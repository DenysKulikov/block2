package com.solvd.laba.patterns.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegularCoffeePreparator implements CoffeePreparator {
    private static final Logger LOGGER = LogManager.getLogger(RegularCoffeePreparator.class);
    @Override
    public void prepareCoffee() {
        LOGGER.info("regular coffee");
    }
}

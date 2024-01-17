package com.solvd.laba.patterns.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SugarCoffeeDecorator implements CoffeePreparator {
    private static final Logger LOGGER = LogManager.getLogger(SugarCoffeeDecorator.class);
    private final CoffeePreparator coffeePreparator;

    public SugarCoffeeDecorator(CoffeePreparator coffeePreparator) {
        this.coffeePreparator = coffeePreparator;
    }

    @Override
    public void prepareCoffee() {
        coffeePreparator.prepareCoffee();
        LOGGER.info("add sugar");
    }
}

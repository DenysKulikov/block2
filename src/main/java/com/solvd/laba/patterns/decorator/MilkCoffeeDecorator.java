package com.solvd.laba.patterns.decorator;

import com.solvd.laba.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MilkCoffeeDecorator implements CoffeePreparator {
    private static final Logger LOGGER = LogManager.getLogger(MilkCoffeeDecorator.class);
    private final CoffeePreparator coffeePreparator;

    public MilkCoffeeDecorator(CoffeePreparator coffeePreparator) {
        this.coffeePreparator = coffeePreparator;
    }

    @Override
    public void prepareCoffee() {
        coffeePreparator.prepareCoffee();
        LOGGER.info("add milk");
    }
}

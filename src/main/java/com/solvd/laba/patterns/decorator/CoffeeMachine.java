package com.solvd.laba.patterns.decorator;

public class CoffeeMachine {
    public static void prepareCoffee(boolean sugar, boolean milk) {
        CoffeePreparator coffeePreparator = new RegularCoffeePreparator();
        if (sugar) {
            coffeePreparator = new SugarCoffeeDecorator(coffeePreparator);
        }
        if (milk) {
            coffeePreparator = new MilkCoffeeDecorator(coffeePreparator);
        }

        coffeePreparator.prepareCoffee();
    }
}

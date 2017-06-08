package com.imesaros.patterns.design.structural

import com.imesaros.patterns.design.structural.decorator.BasicCar
import com.imesaros.patterns.design.structural.decorator.Car
import com.imesaros.patterns.design.structural.decorator.LuxuryCar
import com.imesaros.patterns.design.structural.decorator.SportsCar

class DecoratorSpec {

    public static void main(String[] args) {
        Car sportsCar = new SportsCar(new BasicCar());
        sportsCar.assemble();
        System.out.println("\n*****");

        Car sportsLuxuryCar = new SportsCar(new LuxuryCar(new BasicCar()));
        sportsLuxuryCar.assemble();
    }
}

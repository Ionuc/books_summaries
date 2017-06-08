package com.imesaros.patterns.design.structural.decorator;

public class LuxuryCar extends CarDecorator
{
    public LuxuryCar(Car car)
    {
        super(car);
    }

    @Override
    public void assemble()
    {
        super.assemble();
        System.out.print(" Adding features of Luxury Car.");
    }
}

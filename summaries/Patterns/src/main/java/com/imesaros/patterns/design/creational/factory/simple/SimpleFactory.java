package com.imesaros.patterns.design.creational.factory.simple;

import com.imesaros.patterns.design.creational.factory.objects.car.Audi;
import com.imesaros.patterns.design.creational.factory.objects.car.Car;
import com.imesaros.patterns.design.creational.factory.objects.car.Dacia;

public class SimpleFactory
{
    public Car getCar(String type)
    {
        if (Audi.TYPE.equals(type))
        {
            return new Audi();
        }
        else if (Dacia.TYPE.equals(type))
        {
            return new Dacia();
        }
        throw new UnsupportedOperationException();
    }
}

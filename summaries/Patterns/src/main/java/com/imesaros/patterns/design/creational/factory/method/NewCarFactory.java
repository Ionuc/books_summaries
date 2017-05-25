package com.imesaros.patterns.design.creational.factory.method;

import com.imesaros.patterns.design.creational.factory.objects.car.Audi;
import com.imesaros.patterns.design.creational.factory.objects.car.Bmw;
import com.imesaros.patterns.design.creational.factory.objects.car.Car;

public class NewCarFactory implements MethodFactory
{
    @Override
    public Car getCar(String type)
    {
        if (Audi.TYPE.equals(type))
        {
            return new Audi();
        }
        else if (Bmw.TYPE.equals(type))
        {
            return new Bmw();
        }
        throw new UnsupportedOperationException();
    }
}

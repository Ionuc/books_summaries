package com.imesaros.patterns.design.creational.factory.method;

import com.imesaros.patterns.design.creational.factory.objects.car.Aro;
import com.imesaros.patterns.design.creational.factory.objects.car.Car;
import com.imesaros.patterns.design.creational.factory.objects.car.Dacia;

public class VintageCarFactory implements MethodFactory
{
    @Override
    public Car getCar(String type)
    {
        if (Dacia.TYPE.equals(type))
        {
            return new Dacia();
        }
        else if (Aro.TYPE.equals(type))
        {
            return new Aro();
        }
        throw new UnsupportedOperationException();
    }
}

package com.imesaros.patterns.design.creational.factory.abstractmethod;

import com.imesaros.patterns.design.creational.factory.objects.car.Aro;
import com.imesaros.patterns.design.creational.factory.objects.car.Car;
import com.imesaros.patterns.design.creational.factory.objects.car.Dacia;
import com.imesaros.patterns.design.creational.factory.objects.engine.BigEngine;
import com.imesaros.patterns.design.creational.factory.objects.engine.Engine;
import com.imesaros.patterns.design.creational.factory.objects.engine.LowEngine;

public class RomanianCarFactory implements AbstractMethodFactory
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

    @Override
    public Engine getEngine(String version)
    {
        if (BigEngine.VERSION.equals(version))
        {
            return new BigEngine();
        }
        else if (LowEngine.VERSION.equals(version))
        {
            return new LowEngine();
        }
        throw new UnsupportedOperationException();
    }
}

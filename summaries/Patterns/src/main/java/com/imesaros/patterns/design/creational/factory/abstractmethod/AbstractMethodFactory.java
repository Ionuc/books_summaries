package com.imesaros.patterns.design.creational.factory.abstractmethod;

import com.imesaros.patterns.design.creational.factory.objects.car.Car;
import com.imesaros.patterns.design.creational.factory.objects.engine.Engine;

public interface AbstractMethodFactory
{
    Car getCar(String type);

    Engine getEngine(String version);
}

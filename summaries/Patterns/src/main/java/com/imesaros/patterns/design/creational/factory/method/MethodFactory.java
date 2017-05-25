package com.imesaros.patterns.design.creational.factory.method;

import com.imesaros.patterns.design.creational.factory.objects.car.Car;

public interface MethodFactory
{
    Car getCar(String type);
}

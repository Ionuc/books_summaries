package com.imesaros.patterns.design.creational.factory.objects.car;

public class Bmw implements Car
{
    public static final String TYPE = "BMW";

    @Override
    public String geType()
    {
        return TYPE;
    }
}

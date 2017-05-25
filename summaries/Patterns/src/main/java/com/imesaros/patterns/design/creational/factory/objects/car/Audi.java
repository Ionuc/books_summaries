package com.imesaros.patterns.design.creational.factory.objects.car;

public class Audi implements Car
{
    public static final String TYPE = "AUDI";

    @Override
    public String geType()
    {
        return TYPE;
    }
}

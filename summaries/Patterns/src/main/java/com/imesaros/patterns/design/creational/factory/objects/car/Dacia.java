package com.imesaros.patterns.design.creational.factory.objects.car;

public class Dacia implements Car
{
    public static final String TYPE = "DACIA";

    @Override
    public String geType()
    {
        return TYPE;
    }
}

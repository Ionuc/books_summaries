package com.imesaros.patterns.design.creational.factory.objects.engine;

public class LowEngine implements Engine
{
    public static final String VERSION = "1.0";

    @Override
    public String getVersion()
    {
        return VERSION;
    }
}

package com.imesaros.patterns.design.creational.factory.objects.engine;

public class BigEngine implements Engine
{
    public static final String VERSION = "2.0";

    @Override
    public String getVersion()
    {
        return VERSION;
    }
}

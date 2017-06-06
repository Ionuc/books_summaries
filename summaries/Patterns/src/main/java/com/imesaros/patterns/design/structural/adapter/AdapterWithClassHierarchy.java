package com.imesaros.patterns.design.structural.adapter;

/**
 * Adapter which converts the default 120 Volt object retrieved from Socket into a 12 Volt or 3 Volt
 */
public class AdapterWithClassHierarchy extends Socket implements SocketAdapter
{
    @Override
    public Volt get120Volt()
    {
        return getVolt();
    }

    @Override
    public Volt get12Volt()
    {
        return new Volt(getVolt().getVolts() / 10);
    }

    @Override
    public Volt get3Volt()
    {
        return new Volt(getVolt().getVolts() / 40);
    }
}

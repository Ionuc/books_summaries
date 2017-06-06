package com.imesaros.patterns.design.structural.adapter;

/**
 * Adapter which converts the default 120 Volt object retrieved from Socket into a 12 Volt or 3 Volt
 */
public class AdapterWithObjectHierarchy implements SocketAdapter
{
    private final Socket socket = new Socket();

    @Override
    public Volt get120Volt()
    {
        return socket.getVolt();
    }

    @Override
    public Volt get12Volt()
    {
        return new Volt(socket.getVolt().getVolts() / 10);
    }

    @Override
    public Volt get3Volt()
    {
        return new Volt(socket.getVolt().getVolts() / 40);
    }
}

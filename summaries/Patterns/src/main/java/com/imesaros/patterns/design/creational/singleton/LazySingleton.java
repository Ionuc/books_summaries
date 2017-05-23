package com.imesaros.patterns.design.creational.singleton;

public class LazySingleton implements Singleton
{
    private static Student instance;

    public Student getInstance()
    {
        if (instance == null)
        {
            synchronized (this)
            {
                if (instance == null)
                {
                    instance = new Student();
                }
            }
        }
        return instance;
    }
}

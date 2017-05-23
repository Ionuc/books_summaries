package com.imesaros.patterns.design.creational.singleton;

public class EagerSingleton implements Singleton
{
    private static final Student INSTANCE = new Student();

    public Student getInstance(){
        return INSTANCE;
    }
}

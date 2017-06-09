package com.imesaros.patterns.design.behavioral.templatemethod;

import com.imesaros.patterns.design.behavioral.templatemethod.HouseTemplate;

public class WoodenHouse extends HouseTemplate
{
    @Override
    public void buildWalls()
    {
        System.out.println("Building Wooden Walls");
    }

    @Override
    public void buildPillars()
    {
        System.out.println("Building Pillars with Wood coating");
    }

}

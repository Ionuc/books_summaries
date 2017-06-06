package com.imesaros.patterns.design.structural.composite;

/**
 * A Leaf class
 */
public class Triangle implements Shape
{
    @Override
    public void draw(String color)
    {
        System.out.println("Drawing triangle with color " + color);
    }
}

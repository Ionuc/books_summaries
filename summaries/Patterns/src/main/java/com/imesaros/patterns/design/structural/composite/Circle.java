package com.imesaros.patterns.design.structural.composite;

/**
 * A Leaf class
 */
public class Circle implements Shape
{
    @Override
    public void draw(String color)
    {
        System.out.println("Drawing circle with color " + color);
    }
}

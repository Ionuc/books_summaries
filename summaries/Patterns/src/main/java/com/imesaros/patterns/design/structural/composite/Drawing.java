package com.imesaros.patterns.design.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * The composite object containing the list of {@link Shape}. It provides the helper methods to add & remove a new {@link Shape}
 */
public class Drawing implements Shape
{
    private final List<Shape> shapes = new ArrayList<Shape>();

    @Override
    public void draw(final String color)
    {
        shapes.forEach(s -> s.draw(color));
    }

    public void add(Shape shape)
    {
        shapes.add(shape);
    }

    public void remove(Shape shape)
    {
        shapes.remove(shape);
    }

    public void clear(){
        shapes.clear();
    }
}

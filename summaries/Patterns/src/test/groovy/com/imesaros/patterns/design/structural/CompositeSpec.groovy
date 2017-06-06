package com.imesaros.patterns.design.structural

import com.imesaros.patterns.design.structural.composite.Circle
import com.imesaros.patterns.design.structural.composite.Drawing
import com.imesaros.patterns.design.structural.composite.Shape
import com.imesaros.patterns.design.structural.composite.Triangle
import spock.lang.Specification

class CompositeSpec extends Specification{

    def 'should draw'(){
        expect:
        Shape tri = new Triangle();
        Shape tri1 = new Triangle();
        Shape cir = new Circle();

        Drawing drawing = new Drawing();
        drawing.add(tri1);
        drawing.add(tri1);
        drawing.add(cir);

        drawing.draw("Red");

        drawing.clear();

        drawing.add(tri);
        drawing.add(cir);
        drawing.draw("Green");
    }
}

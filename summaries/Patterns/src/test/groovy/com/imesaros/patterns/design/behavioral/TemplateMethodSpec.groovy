package com.imesaros.patterns.design.behavioral

import com.imesaros.patterns.design.behavioral.templatemethod.GlassHouse
import com.imesaros.patterns.design.behavioral.templatemethod.HouseTemplate
import com.imesaros.patterns.design.behavioral.templatemethod.WoodenHouse

class TemplateMethodSpec {
    public static void main(String[] args) {

        HouseTemplate houseType = new WoodenHouse();

        //using template method
        houseType.buildHouse();
        System.out.println("************");

        houseType = new GlassHouse();

        houseType.buildHouse();
    }
}

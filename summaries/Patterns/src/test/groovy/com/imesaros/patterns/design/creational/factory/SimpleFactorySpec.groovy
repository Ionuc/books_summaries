package com.imesaros.patterns.design.creational.factory

import com.imesaros.patterns.design.creational.factory.objects.car.Audi
import com.imesaros.patterns.design.creational.factory.objects.car.Dacia
import com.imesaros.patterns.design.creational.factory.simple.SimpleFactory
import spock.lang.Specification

class SimpleFactorySpec extends Specification {

    def factory = new SimpleFactory()

    def 'should create Car'() {
        expect:
        factory.getCar(type).geType().equals(expected)

        where:
        type       | expected
        Audi.TYPE  | Audi.TYPE
        Dacia.TYPE | Dacia.TYPE
    }
}

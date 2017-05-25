package com.imesaros.patterns.design.creational.factory

import com.imesaros.patterns.design.creational.factory.objects.car.Audi
import com.imesaros.patterns.design.creational.factory.objects.car.Dacia
import com.imesaros.patterns.design.creational.factory.staticmethod.StaticMethodFactory
import spock.lang.Specification

class StaticMethodFactorySpec extends Specification {

    def 'should create Car'() {
        expect:
        StaticMethodFactory.getCar(type).geType().equals(expected)

        where:
        type       | expected
        Audi.TYPE  | Audi.TYPE
        Dacia.TYPE | Dacia.TYPE
    }
}

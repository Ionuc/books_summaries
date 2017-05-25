package com.imesaros.patterns.design.creational.factory

import com.imesaros.patterns.design.creational.factory.abstractmethod.RomanianCarFactory
import com.imesaros.patterns.design.creational.factory.objects.car.Aro
import com.imesaros.patterns.design.creational.factory.objects.car.Dacia
import com.imesaros.patterns.design.creational.factory.objects.engine.BigEngine
import com.imesaros.patterns.design.creational.factory.objects.engine.LowEngine
import spock.lang.Specification

class AbstractMethodFactorySpec extends Specification {

    RomanianCarFactory romanianCarFactory = new RomanianCarFactory()

    def 'should create romanian cars with the corresponding engine'() {
        expect:
        romanianCarFactory.getCar(type).geType().equals(expectedType)
        romanianCarFactory.getEngine(version).getVersion().equals(expectedVersion)

        where:
        type       | expectedType | version           | expectedVersion
        Dacia.TYPE | Dacia.TYPE   | BigEngine.VERSION | BigEngine.VERSION
        Aro.TYPE   | Aro.TYPE     | BigEngine.VERSION | BigEngine.VERSION
        Dacia.TYPE | Dacia.TYPE   | LowEngine.VERSION | LowEngine.VERSION
        Aro.TYPE   | Aro.TYPE     | LowEngine.VERSION | LowEngine.VERSION
    }
}

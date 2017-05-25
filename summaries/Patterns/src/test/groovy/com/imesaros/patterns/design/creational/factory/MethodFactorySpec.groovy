package com.imesaros.patterns.design.creational.factory

import com.imesaros.patterns.design.creational.factory.method.NewCarFactory
import com.imesaros.patterns.design.creational.factory.method.VintageCarFactory
import com.imesaros.patterns.design.creational.factory.objects.car.Aro
import com.imesaros.patterns.design.creational.factory.objects.car.Audi
import com.imesaros.patterns.design.creational.factory.objects.car.Bmw
import com.imesaros.patterns.design.creational.factory.objects.car.Dacia
import spock.lang.Shared
import spock.lang.Specification

class MethodFactorySpec extends Specification {

    @Shared
    NewCarFactory newCarFactory = new NewCarFactory()
    @Shared
    VintageCarFactory vintageCarFactory = new VintageCarFactory()

    def 'should create Car'() {
        expect:
        methodFactory.getCar(type).geType().equals(expected)

        where:
        methodFactory     | type       | expected
        newCarFactory     | Audi.TYPE  | Audi.TYPE
        newCarFactory     | Bmw.TYPE   | Bmw.TYPE
        vintageCarFactory | Dacia.TYPE | Dacia.TYPE
        vintageCarFactory | Aro.TYPE   | Aro.TYPE
    }

    def 'should throw exception'() {
        when:
        methodFactory.getCar(type)
        then:
        thrown(UnsupportedOperationException)

        where:
        methodFactory     | type
        vintageCarFactory | Audi.TYPE
        vintageCarFactory | Bmw.TYPE
        newCarFactory     | Dacia.TYPE
        newCarFactory     | Aro.TYPE
    }
}

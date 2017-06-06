package com.imesaros.patterns.design.structural

import com.imesaros.patterns.design.structural.adapter.AdapterWithClassHierarchy
import com.imesaros.patterns.design.structural.adapter.AdapterWithObjectHierarchy
import spock.lang.Specification

class AdapterSpec extends Specification {

    def 'should create 3 Volt'() {
        expect:
        socketAdapter.get3Volt().getVolts() == 3

        where:
        socketAdapter << [
                new AdapterWithClassHierarchy(),
                new AdapterWithObjectHierarchy()
        ]
    }

    def 'should create 12 Volt'() {
        expect:
        socketAdapter.get12Volt().getVolts() == 12

        where:
        socketAdapter << [
                new AdapterWithClassHierarchy(),
                new AdapterWithObjectHierarchy()
        ]
    }

    def 'should create 120 Volt'() {
        expect:
        socketAdapter.get120Volt().getVolts() == 120

        where:
        socketAdapter << [
                new AdapterWithClassHierarchy(),
                new AdapterWithObjectHierarchy()
        ]
    }
}

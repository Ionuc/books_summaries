package com.imesaros.crackingthetechnicalinterview.chapter2

import spock.lang.Specification

import java.util.stream.IntStream


class CustomHashMapWithDynamicArraySpecs extends Specification {

    CustomHashMapWithDynamicArray map = new CustomHashMapWithDynamicArray()

    def 'should put values'() {
        when:
        IntStream.range(0, 1000)
                .forEach({ i -> map.put(i, i.toString()) })

        then:
        IntStream.range(0, 1000)
                .forEach({ i -> assert map.get(i) == i.toString() })
    }

    def 'should remove values'() {
        given:
        IntStream.range(0, 1000)
                .forEach({ i -> map.put(i, i.toString()) })

        when:
        IntStream.range(0, 1000)
                .forEach({ i -> map.remove(i) })

        then:
        IntStream.range(0, 1000)
                .forEach({ i -> assert map.get(i) == null })
    }
}

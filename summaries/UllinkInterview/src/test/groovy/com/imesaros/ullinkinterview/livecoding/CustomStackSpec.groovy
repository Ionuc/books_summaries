package com.imesaros.ullinkinterview.livecoding

import spock.lang.Shared
import spock.lang.Specification

class CustomStackSpec extends Specification{

    @Shared
    CustomStackWithArray stackWithArray = new CustomStackWithArray(1)
    @Shared
    CustomStackWithList stackWithList = new CustomStackWithList()


    def 'should push & pop elements'(){
        when:
        stack.push(3)
        stack.push(5)

        then:
        stack.pop() == 5
        stack.pop() == 3
        stack.pop() == null

        where:
        stack << [
                stackWithArray,
                stackWithList
        ]
    }

    def 'should inc element'(){
        given:
        stack.push(3)
        stack.push(5)

        when:
        stack.inc(1, 3)

        then:
        stack.pop() == 5
        stack.pop() == 6

        where:
        stack << [
                stackWithArray,
                stackWithList
        ]
    }
}

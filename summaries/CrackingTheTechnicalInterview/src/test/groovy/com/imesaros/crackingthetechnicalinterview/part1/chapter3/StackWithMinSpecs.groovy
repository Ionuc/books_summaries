package com.imesaros.crackingthetechnicalinterview.part1.chapter3

import spock.lang.Specification


class StackWithMinSpecs extends Specification {

    def 'should test add(), remove() and min()'() {
        given:
        StackWithMin stack = new StackWithMin()
        stack.push(3)
        stack.push(5)
        stack.push(7)
        stack.push(1)
        stack.push(2)

        expect:
        stack.min() == 1
        stack.pop() == 2

        stack.min() == 1
        stack.pop() == 1

        stack.min() == 3
        stack.pop() == 7

        stack.min() == 3
        stack.pop() == 5

        stack.min() == 3
        stack.pop() == 3

        stack.min() == null
        stack.pop() == null

    }
}

package com.imesaros.crackingthetechnicalinterview.part1.chapter3

import spock.lang.Specification


class StackWithLinkedListNodeSpecs extends Specification {

    def 'should add and remove one element from queue'() {
        given:
        StackWithLinkedListNode stack = new StackWithLinkedListNode()
        stack.push(3)
        stack.push(5)
        stack.push(7)

        when:
        Integer result = stack.pop()

        then:
        result == 7

        when:
        result = stack.pop()

        then:
        result == 5

        when:
        result = stack.pop()

        then:
        result == 3

        when:
        result = stack.pop()

        then:
        result == null
    }
}

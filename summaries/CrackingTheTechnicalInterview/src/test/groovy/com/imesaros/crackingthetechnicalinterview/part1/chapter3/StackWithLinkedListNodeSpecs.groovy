package com.imesaros.crackingthetechnicalinterview.part1.chapter3

import spock.lang.Specification


class StackWithLinkedListNodeSpecs extends Specification {

    def 'should add and remove one element from queue'() {
        given:
        StackWithLinkedListNode stack = new StackWithLinkedListNode()
        stack.push(3)
        stack.push(5)
        stack.push(7)

        expect:
        stack.pop() == 7
        stack.pop() == 5
        stack.pop() == 3
        stack.pop() == null
    }
}

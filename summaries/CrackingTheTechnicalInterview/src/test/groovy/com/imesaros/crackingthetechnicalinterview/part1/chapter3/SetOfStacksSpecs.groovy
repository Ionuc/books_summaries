package com.imesaros.crackingthetechnicalinterview.part1.chapter3

import spock.lang.Specification


class SetOfStacksSpecs extends Specification {

    def 'should add new Stack when its capacity was achived'() {
        given:
        SetOfStacks stack = new SetOfStacks(2)

        when:
        stack.push(3)

        then:
        stack.stacks.size() == 1

        when:
        stack.push(5)

        then:
        stack.stacks.size() == 1

        when:
        stack.push(7)

        then:
        stack.stacks.size() == 2

        when:
        stack.push(9)

        then:
        stack.stacks.size() == 2

        when:
        stack.push(10)

        then:
        stack.stacks.size() == 3
    }

    def 'should remove elements from stack'() {
        given:
        SetOfStacks stack = new SetOfStacks(2)
        stack.push(3)
        stack.push(5)
        stack.push(7)
        stack.push(1)
        stack.push(2)

        expect:
        stack.stacks.size() == 3
        stack.pop() == 2
        stack.stacks.size() == 2
        stack.pop() == 1
        stack.stacks.size() == 2
        stack.pop() == 7
        stack.stacks.size() == 1
        stack.pop() == 5
        stack.stacks.size() == 1
        stack.pop() == 3
        stack.stacks.size() == 0
        stack.pop() == null
        stack.stacks.size() == 0
    }
}

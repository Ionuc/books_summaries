package com.imesaros.crackingthetechnicalinterview.part1.chapter3

import spock.lang.Specification


class MyQueueSpecs extends Specification {

    def 'should add and remove elements'() {
        given:
        MyQueue queue = new MyQueue()
        queue.enqueue(3)
        queue.enqueue(5)
        queue.enqueue(7)

        expect:
        queue.dequeue() == 3
        queue.dequeue() == 5
        queue.dequeue() == 7
        queue.dequeue() == null

    }
}

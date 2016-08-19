package com.imesaros.crackingthetechnicalinterview.part1.chapter3

import spock.lang.Specification


class QueueWithLinkedListNodeSpecs extends Specification {

    def 'should add and remove one element from queue'() {
        given:
        QueueWithLinkedListNode queue = new QueueWithLinkedListNode()
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

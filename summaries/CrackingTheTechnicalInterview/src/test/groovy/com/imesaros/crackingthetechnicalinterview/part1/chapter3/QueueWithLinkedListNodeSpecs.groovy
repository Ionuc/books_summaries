package com.imesaros.crackingthetechnicalinterview.part1.chapter3

import spock.lang.Specification


class QueueWithLinkedListNodeSpecs extends Specification {

    def 'should add and remove one element from queue'() {
        given:
        QueueWithLinkedListNode queue = new QueueWithLinkedListNode()
        queue.enqueue(3)
        queue.enqueue(5)
        queue.enqueue(7)

        when:
        Integer result = queue.dequeue()

        then:
        result == 3

        when:
        result = queue.dequeue()

        then:
        result == 5

        when:
        result = queue.dequeue()

        then:
        result == 7

        when:
        result = queue.dequeue()

        then:
        result == null
    }
}

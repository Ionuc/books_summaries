package com.imesaros.crackingthetechnicalinterview.chapter2

import spock.lang.Specification

import static com.imesaros.crackingthetechnicalinterview.chapter2.LinkedListHelper.removeDuplicatesWithBuffer
import static com.imesaros.crackingthetechnicalinterview.chapter2.LinkedListHelper.removeDuplicatesWithoutBuffer


class LinkedListHelperSpecs extends Specification {

    def 'should remove duplicates using buffer'() {
        when:
        removeDuplicatesWithBuffer(head)

        then:
        while (expected != null) {
            assert expected.getItem() == head.getItem()
            expected = expected.getNext()
            head = head.getNext()
        }
        where:
        head                                             | expected
        createLinkedList([1, 2, 3, 4, 5])                | createLinkedList([1, 2, 3, 4, 5])
        createLinkedList([1, 2, 3, 4, 5, 1, 2, 3, 4, 5]) | createLinkedList([1, 2, 3, 4, 5])
        createLinkedList([1, 1, 1, 1, 1])                | createLinkedList([1])
    }

    def 'should remove duplicates without using buffer'() {
        when:
        removeDuplicatesWithoutBuffer(head)

        then:
        while (expected != null) {
            assert expected.getItem() == head.getItem()
            expected = expected.getNext()
            head = head.getNext()
        }
        where:
        head                                             | expected
        createLinkedList([1, 2, 3, 4, 5])                | createLinkedList([1, 2, 3, 4, 5])
        createLinkedList([1, 2, 3, 4, 5, 1, 2, 3, 4, 5]) | createLinkedList([1, 2, 3, 4, 5])
        createLinkedList([1, 1, 1, 1, 1])                | createLinkedList([1])
    }

    private static LinkedListNode createLinkedList(List<Integer> integers) {
        LinkedListNode head = new LinkedListNode(integers.get(0))
        LinkedListNode list = head
        for (int i = 1; i < integers.size(); i++) {
            LinkedListNode n = new LinkedListNode(integers.get(i))
            head.setNext(n);
            head = n;

        }
        return list
    }
}

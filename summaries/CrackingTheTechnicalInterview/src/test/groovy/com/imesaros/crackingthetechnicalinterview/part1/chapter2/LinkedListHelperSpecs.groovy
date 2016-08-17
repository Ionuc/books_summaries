package com.imesaros.crackingthetechnicalinterview.part1.chapter2

import spock.lang.Specification

import static com.imesaros.crackingthetechnicalinterview.part1.chapter2.LinkedListHelper.*

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

    def 'should add two nodes'() {
        when:
        LinkedListNode result = add(root1, root2);

        then:
        while (expected != null) {
            assert expected.getItem() == result.getItem()
            expected = expected.getNext()
            result = result.getNext()
        }

        where:
        root1                       | root2                          | expected
        createLinkedList([3, 1, 5]) | createLinkedList([5, 9, 2])    | createLinkedList([8, 0, 8])
        createLinkedList([3, 1, 5]) | createLinkedList([5, 9, 4])    | createLinkedList([8, 0, 0, 1])
        createLinkedList([3, 1, 5]) | createLinkedList([5, 9, 4, 1]) | createLinkedList([8, 0, 0, 2])
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

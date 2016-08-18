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
        root1                       | root2                                | expected
        createLinkedList([3, 1, 5]) | createLinkedList([5, 9, 2])          | createLinkedList([8, 0, 8])
        createLinkedList([3, 1, 5]) | createLinkedList([5, 9, 4])          | createLinkedList([8, 0, 0, 1])
        createLinkedList([3, 1, 5]) | createLinkedList([5, 9, 4, 1])       | createLinkedList([8, 0, 0, 2])
        createLinkedList([3, 1, 5]) | createLinkedList([5, 9, 4, 1, 1, 1]) | createLinkedList([8, 0, 0, 2, 1, 1])
    }


    static LinkedListNode NODE1 = createNode(1)
    static LinkedListNode NODE2 = createNode(2)
    static LinkedListNode NODE3 = createNode(3)
    static LinkedListNode NODE4 = createNode(4)
    static LinkedListNode NODE5 = createNode(5)
    static LinkedListNode NODE6 = createNode(6)
    static LinkedListNode NODE7 = createNode(7)


    def 'should find first node which is creating a cycle'() {
        when:
        LinkedListNode result = findFirstLoop(root)

        then:
        result == expected

        where:
        root                      | expected
        link(NODE1, NODE2, NODE1) | NODE1
        link(NODE3, NODE4, NODE4) | NODE4
        link(NODE5, NODE6)        | null
        link(NODE7)               | null
        null                      | null
    }

    private static LinkedListNode link(LinkedListNode[] nodes) {
        for (int i = 0; i < nodes.length - 1; i++) {
            nodes[i].setNext(nodes[i + 1])
        }
        return nodes[0]
    }

    private static LinkedListNode createNode(int value) {
        return new LinkedListNode(value)
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

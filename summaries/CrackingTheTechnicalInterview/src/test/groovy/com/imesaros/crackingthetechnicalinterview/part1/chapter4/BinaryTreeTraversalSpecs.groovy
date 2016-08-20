package com.imesaros.crackingthetechnicalinterview.part1.chapter4

import spock.lang.Specification

import java.util.function.Consumer

import static com.imesaros.crackingthetechnicalinterview.part1.chapter4.BinaryTreeTraversal.*

class BinaryTreeTraversalSpecs extends Specification {

    /**
     * given Tree =>
     *      8
     *   4      12
     * 2   6  10  14
     */
    BinaryTree root = new BinaryTree(
            8,
            new BinaryTree(
                    4,
                    new BinaryTree(2),
                    new BinaryTree(6)),
            new BinaryTree(
                    12,
                    new BinaryTree(10),
                    new BinaryTree(14)))

    List<Integer> list = new ArrayList<>()
    Consumer<Integer> consumer = { value -> list.add(value); }

    def 'should travers in IN ORDER'() {
        when:
        traverInOrder(root, consumer)

        then:
        List<Integer> expected = [2, 4, 6, 8, 10, 12, 14]
        list == expected
    }

    def 'should travers in PRE ORDER'() {
        when:
        traverPreOrder(root, consumer)

        then:
        List<Integer> expected = [8, 4, 2, 6, 12, 10, 14]
        list == expected
    }

    def 'should travers in POST ORDER'() {
        when:
        traverPostOrder(root, consumer)

        then:
        List<Integer> expected = [2, 6, 4, 10, 14, 12, 8]
        list == expected
    }
}

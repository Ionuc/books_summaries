package com.imesaros.crackingthetechnicalinterview.part1.chapter4

import spock.lang.Specification

import java.util.function.Consumer

import static com.imesaros.crackingthetechnicalinterview.part1.chapter4.BinaryTreeHelper.*
import static com.imesaros.crackingthetechnicalinterview.part1.chapter4.BinaryTreeTraversal.traverInOrder

class BinaryTreeHelperSpecs extends Specification {

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

    def 'should insert new value'() {
        when:
        insert(root, value)

        then:
        traverInOrder(root, consumer)
        list == expected

        where:
        value | expected
        1     | [1, 2, 4, 6, 8, 10, 12, 14]
        3     | [2, 3, 4, 6, 8, 10, 12, 14]
        5     | [2, 4, 5, 6, 8, 10, 12, 14]
        7     | [2, 4, 6, 7, 8, 10, 12, 14]
        9     | [2, 4, 6, 8, 9, 10, 12, 14]
        11    | [2, 4, 6, 8, 10, 11, 12, 14]
        13    | [2, 4, 6, 8, 10, 12, 13, 14]
        15    | [2, 4, 6, 8, 10, 12, 14, 15]
    }

    def 'should test if a binary tree is balanced or not'() {
        expect:
        isBalanced(root) == expected

        where:
        root                                                                | expected
        null                                                                | true
        new BinaryTree(3)                                                   | true
        new BinaryTree(3, null, new BinaryTree(4))                          | true
        new BinaryTree(3, new BinaryTree(2), null)                          | true
        new BinaryTree(3, new BinaryTree(2), new BinaryTree(4))             | true
        new BinaryTree(3, new BinaryTree(2, new BinaryTree(1), null), null) | false
    }

    def 'should create balanced binary search tree from array'() {

        when:
        BinaryTree root = createBinarySearchTree(integers.toArray(new Integer[integers.size()]))

        then:
        isBalanced(root)
        traverInOrder(root, consumer)
        list == integers

        where:
        integers << [
                [1],
                [1, 2],
                [1, 2, 3],
                [1, 2, 3, 4],
                [1, 2, 3, 4, 5],
                [1, 2, 3, 4, 5, 6]
        ]
    }
}

package com.imesaros.crackingthetechnicalinterview.part1.chapter1

import spock.lang.Specification

class FibonacciSpecs extends Specification {

    Fibonacci algorithm = new Fibonacci()

    def 'should generate the n\'th element with formula 1'() {
        expect:
        algorithm.generate1(n) == expected

        where:
        n  | expected
        -2 | -1
        0  | 0
        1  | 1
        5  | 5
        10 | 55
    }

    def 'should generate the n\'th element with formula 2'() {
        expect:
        algorithm.generate2(n) == expected

        where:
        n  | expected
        -2 | -1
        0  | 0
        1  | 1
        5  | 5
        10 | 55
    }

    def 'should generate the n\'th element with formula 3'() {
        expect:
        algorithm.generate3(n) == expected

        where:
        n  | expected
        -2 | -1
        0  | 0
        1  | 1
        5  | 5
        10 | 55
    }
}

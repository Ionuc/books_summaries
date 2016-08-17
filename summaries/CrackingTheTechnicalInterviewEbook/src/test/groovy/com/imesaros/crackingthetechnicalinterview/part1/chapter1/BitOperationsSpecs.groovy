package com.imesaros.crackingthetechnicalinterview.part1.chapter1

import spock.lang.Specification

class BitOperationsSpecs extends Specification {

    BitOperations algorithm = new BitOperations()

    def 'should add two numbers for first formula 1'() {
        expect:
        algorithm.add(x, y) == expected

        where:
        x  | y  | expected
        2  | 3  | 5
        3  | 2  | 5
        -3 | 6  | 3
        6  | -3 | 3
        0  | 7  | 7
        7  | 0  | 7
    }
}

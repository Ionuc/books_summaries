package com.imesaros.crackingthetechnicalinterview.part1.chapter1

import spock.lang.Specification

class LeastCommonMultipleSpecs extends Specification {

    LeastCommonMultiple algorithm = new LeastCommonMultiple()

    def 'should compute least common multiplier formula 1'() {
        expect:
        algorithm.leastCommonMultiple1(a, b) == expected

        where:
        a  | b  | expected
        3  | 5  | 15
        5  | 3  | 15
        4  | 6  | 12
        6  | 4  | 12
        -2 | 4  | -1
        0  | 4  | -1
        6  | 0  | -1
        6  | -4 | -1
    }

    def 'should compute least common multiplier formula 2'() {
        expect:
        algorithm.leastCommonMultiple2(a, b) == expected

        where:
        a  | b  | expected
        3  | 5  | 15
        5  | 3  | 15
        4  | 6  | 12
        6  | 4  | 12
        -2 | 4  | -1
        0  | 4  | -1
        6  | 0  | -1
        6  | -4 | -1
    }
}

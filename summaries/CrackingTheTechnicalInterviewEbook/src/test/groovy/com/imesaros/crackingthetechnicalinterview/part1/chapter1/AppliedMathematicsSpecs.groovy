package com.imesaros.crackingthetechnicalinterview.part1.chapter1

import spock.lang.Specification

import static java.util.Optional.empty
import static java.util.Optional.of

class AppliedMathematicsSpecs extends Specification {

    AppliedMathematics algorithm = new AppliedMathematics()

    def 'should multiple two numbers using only "+" operator'() {
        expect:
        algorithm.multipleWithAdd(x, y) == expected

        where:
        x  | y  | expected
        5  | 6  | 30
        -5 | 6  | -30
        5  | -6 | -30
        -5 | -6 | 30
        5  | 0  | 0
        -5 | 0  | 0
        0  | 5  | 0
        0  | -5 | 0
    }

    def 'should subtract two numbers using only "+" operator'() {
        expect:
        algorithm.subtractWithAdd(x, y) == expected

        where:
        x  | y  | expected
        5  | 6  | -1
        -5 | 6  | -11
        5  | -6 | 11
        -5 | -6 | 1
        5  | 0  | 5
        -5 | 0  | -5
        0  | 5  | -5
        0  | -5 | 5
    }

    def 'should divide two numbers using only "+" operator'() {
        expect:
        algorithm.divideWithAdd(x, y) == expected

        where:
        x  | y  | expected
        5  | 6  | of(0)
        5  | -6 | of(0)
        -5 | 6  | of(-1)
        -5 | -6 | of(1)

        7  | 3  | of(2)
        7  | -3 | of(-2)
        -7 | 3  | of(-3)
        -7 | -3 | of(3)

        5  | 0  | empty()
        -5 | 0  | empty()
        0  | 5  | of(0)
        0  | -5 | of(0)
    }
}

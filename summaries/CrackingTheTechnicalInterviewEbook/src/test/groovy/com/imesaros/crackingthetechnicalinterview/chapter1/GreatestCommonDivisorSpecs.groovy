package com.imesaros.crackingthetechnicalinterview.chapter1

import spock.lang.Specification

class GreatestCommonDivisorSpecs extends Specification {

    GreatestCommonDivisor algorithm = new GreatestCommonDivisor()

    def 'should compute greatest common divisor with formula 1'() {
        expect:
        algorithm.greatestCommonDivisor1(a, b) == expected

        where:
        a  | b  | expected
        3  | 5  | 1
        5  | 3  | 1
        4  | 6  | 2
        6  | 4  | 2
        -2 | 4  | -1
        0  | 4  | -1
        6  | 0  | -1
        6  | -4 | -1
    }

    def 'should compute greatest common divisor with formula 2'() {
        expect:
        algorithm.greatestCommonDivisor2(a, b) == expected

        where:
        a  | b  | expected
        3  | 5  | 1
        5  | 3  | 1
        4  | 6  | 2
        6  | 4  | 2
        -2 | 4  | -1
        0  | 4  | -1
        6  | 0  | -1
        6  | -4 | -1
    }
}

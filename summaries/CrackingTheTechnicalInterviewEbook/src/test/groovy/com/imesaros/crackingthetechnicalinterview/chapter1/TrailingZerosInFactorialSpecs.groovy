package com.imesaros.crackingthetechnicalinterview.chapter1

import spock.lang.Specification

class TrailingZerosInFactorialSpecs extends Specification {

    TrailingZerosInFactorial algorithm = new TrailingZerosInFactorial()

    def 'should compute total trailing zeros in n factorial'() {
        expect:
        algorithm.trailingZeros(n) == expected

        where:
        n  | expected
        -1 | 0
        4  | 0
        5  | 1
        9  | 1
        10 | 2
        14 | 2
        15 | 3
        19 | 3
        20 | 4
        24 | 4
        25 | 6
        29 | 6
        30 | 7
    }
}

package com.imesaros.crackingthetechnicalinterview.chapter1

import spock.lang.Specification

class KthNumberWithGivenFactorsSpecs extends Specification {

    KthNumberWithGivenFactors algorithm = new KthNumberWithGivenFactors()

    def 'should generate kt\'th element'() {
        expect:
        algorithm.findKthElement(k) == expected

        where:
        k  | expected
        1  | 3
        2  | 5
        3  | 7
        4  | 9
        5  | 15
        6  | 21
        7  | 25
        8  | 27
        9  | 35
        10 | 45
        11 | 49
        12 | 63
    }
}

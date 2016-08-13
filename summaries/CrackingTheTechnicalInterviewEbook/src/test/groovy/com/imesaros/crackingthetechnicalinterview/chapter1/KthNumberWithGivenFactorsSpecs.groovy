package com.imesaros.crackingthetechnicalinterview.chapter1

import spock.lang.Specification

import static java.util.Arrays.asList

class KthNumberWithGivenFactorsSpecs extends Specification {

    KthNumberWithGivenFactors algorithm = new KthNumberWithGivenFactors()

    def 'should generate kt\'th element using factors of 3, 5 and 7'() {
        expect:
        algorithm.findKthElementWithFactors3And5And7(k) == expected

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

    def 'should generate kt\'th element using factors of given prime factors'() {
        expect:
        algorithm.findKthElementWithFactors(k, primes) == expected

        where:
        k  | primes           | expected
        1  | asList(5, 7, 11) | 5
        2  | asList(5, 7, 11) | 7
        3  | asList(5, 7, 11) | 11
        4  | asList(5, 7, 11) | 25
        5  | asList(5, 7, 11) | 35
        6  | asList(5, 7, 11) | 49
        7  | asList(5, 7, 11) | 55
        8  | asList(5, 7, 11) | 77
        9  | asList(5, 7, 11) | 121
        10 | asList(5, 7, 11) | 125
        11 | asList(5, 7, 11) | 175
        12 | asList(5, 7, 11) | 245
    }
}

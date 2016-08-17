package com.imesaros.crackingthetechnicalinterview.chapter1

import spock.lang.Specification

import static com.imesaros.crackingthetechnicalinterview.chapter1.ArraysAndStrings.*

class ArraysAndStringsSpecs extends Specification {

    def 'should test if a string has only unique chars with formula 1'() {
        expect:
        isUniqueChars1(input) == expected

        where:
        input  | expected
        'test' | false
        'tes'  | true
    }

    def 'should test if a string has only unique chars with formula 2'() {
        expect:
        isUniqueChars2(input) == expected

        where:
        input  | expected
        'test' | false
        'tes'  | true
    }

    def 'should remove duplicates chars'() {
        expect:
        removeDuplicates(input).equals(expected)

        where:
        input        | expected
        'ionucionuc' | 'ionuc'
        'mtesty'     | 'mtesy'
        'test'       | 'tes'
    }
}

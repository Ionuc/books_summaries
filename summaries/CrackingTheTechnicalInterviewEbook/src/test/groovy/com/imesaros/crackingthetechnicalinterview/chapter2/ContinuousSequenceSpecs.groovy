package com.imesaros.crackingthetechnicalinterview.chapter2

import spock.lang.Specification

import static com.imesaros.crackingthetechnicalinterview.chapter2.ContinuousSequence.withLargestSum

class ContinuousSequenceSpecs extends Specification {

    def 'should get the larges values'() {
        expect:
        withLargestSum(list) == expected

        where:
        list                             | expected
        [2, -8, 3, -2, 4, -10]           | [3, -2, 4]
        [2, -8, 3, -2, 4, -10, 6]        | [6]
        [5, -1, 2, -8, 3, -2, 4, -10, 6] | [5, -1, 2]
    }
}

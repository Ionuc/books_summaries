package com.imesaros.crackingthetechnicalinterview.part1.chapter1

import spock.lang.Specification

class TotalAppearancesOfDigitInNumbersFromAnIntervalSpecs extends Specification {
    TotalAppearancesOfDigitInNumbersFromAnInterval algorithm = new TotalAppearancesOfDigitInNumbersFromAnInterval()

    def 'should count total appearances of a given digit inside [0, n]'() {
        expect:
        algorithm.countNumberOfADigitFrom0ToN(n, k) == expected

        where:
        n   | k | expected
        1   | 2 | 0
        2   | 2 | 1
        9   | 2 | 1
        11  | 2 | 1
        12  | 2 | 2
        19  | 2 | 2
        20  | 2 | 3
        21  | 2 | 4
        22  | 2 | 6
        29  | 2 | 13
        49  | 2 | 15
        99  | 2 | 20
        101 | 2 | 20
        102 | 2 | 21
        111 | 2 | 21
        112 | 2 | 22
        119 | 2 | 22
        120 | 2 | 23
        121 | 2 | 24
        122 | 2 | 26
    }
}

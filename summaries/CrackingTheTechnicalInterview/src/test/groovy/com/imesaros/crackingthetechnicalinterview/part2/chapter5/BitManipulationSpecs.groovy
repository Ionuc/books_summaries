package com.imesaros.crackingthetechnicalinterview.part2.chapter5

import spock.lang.Specification

import static com.imesaros.crackingthetechnicalinterview.part2.chapter5.BitManipulation.*

class BitManipulationSpecs extends Specification {

    static byte ZERO = 0;
    static byte ONE = 1;

    def 'should update bits'() {
        expect:
        updateBits(1024, b, i, 4 + i) == 1024 + b * (1 << i)

        where:
        b  | i
        21 | 2
        14 | 3
        15 | 4
    }

    def 'should create binary representation for float'() {
        expect:
        createBinaryRepresentation(value) == expected

        where:
        value    | expected
        "12"     | "1100"
        "12.625" | "1100.101"
    }

    def 'should count the bits changed to convert a to b'() {
        expect:
        nrBitsToConvert(a, b) == expected

        where:
        a                  | b                  | expected
        create(1, 0, 1, 0) | create(1, 0, 1, 1) | 1
        create(1, 0, 1, 0) | create(1, 1, 0, 0) | 2
        create(1, 0, 1, 0) | create(1, 1, 0, 1) | 3
        create(1, 0, 1, 0) | create(0, 1, 0, 1) | 4
    }

    def 'should swap odd bits with even bits'() {
        expect:
        swapOddWithEven(value) == expected

        where:
        value                       | expected
        create(1, 0, 1, 0)          | create(0, 1, 0, 1)
        create(1, 0, 1, 0, 1, 0)    | create(0, 1, 0, 1, 0, 1)
        create(0, 1, 0, 1, 0, 1, 0) | create(0, 0, 1, 0, 1, 0, 1)
    }

    private static int create(Integer... bytes) {
        int value = 0;
        for (int i = bytes.length - 1; i >= 0; i--) {
            value += bytes[i] * Math.pow(2, bytes.length - 1 - i)
        }
        return value
    }
}

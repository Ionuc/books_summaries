package com.imesaros.crackingthetechnicalinterview.part1.chapter1;


public class TrailingZerosInFactorial {

    public int trailingZeros(int n) {
        if (n < 5) {
            return 0;
        }
        int count = 0;
        while (n > 0) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }
}

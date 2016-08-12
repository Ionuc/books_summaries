package com.imesaros.crackingthetechnicalinterview.chapter1;


import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

/**
 * Write a method to implement *, - , / operations. You should use only the + operator.
 */
public class AppliedMathematics {


    /**
     * Multiple
     */
    public int multipleWithAdd(int x, int y) {
        int max = Math.max(y, 0);
        int min = Math.min(y, 0);

        int count = 0;
        for (int i = min; i < max; i++) {
            count += x;
        }
        return y > 0 ? count : -count;
    }

    /**
     * Subtract
     */
    public int subtractWithAdd(int x, int y) {
        return x + -y;
    }

    /**
     * Divide
     */
    public Optional<Integer> divideWithAdd(Integer x, Integer y) {
        if (y == 0) {
            return empty();
        }
        int count = 0;
        Integer a = Math.abs(x);
        Integer b = Math.abs(y);
        while (a >= b) {
            count++;
            a -= b;
        }
        if (x < 0) {
            count++;
            return y < 0 ? of(count) : of(-count);
        }
        return y > 0 ? of(count) : of(-count);
    }
}

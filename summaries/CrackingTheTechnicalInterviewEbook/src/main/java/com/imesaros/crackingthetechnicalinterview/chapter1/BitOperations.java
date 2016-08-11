package com.imesaros.crackingthetechnicalinterview.chapter1;

public class BitOperations {

    /**
     * Problem : Write a function that adds two numbers. You should not use + or any arithmetic operators
     * XOR (x ^ y) is addition without carry.  (x & y) is the carry-out from each bit.  (x & y) << 1 is the carry-in to each bit.
     * The loop keeps adding the carries until the carry is zero for all bits.
     *
     * @param x first number
     * @param y second number
     * @return x + y
     */
    public int add(int x, int y) {
        int a, b;
        do {
            a = x & y;
            b = x ^ y;
            x = a << 1;
            y = b;
        } while (a != 0);
        return b;
    }
}

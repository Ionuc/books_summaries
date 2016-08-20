package com.imesaros.crackingthetechnicalinterview.part2.chapter5;

public class BitManipulation {

    /**
     * Problem 1
     * You are given two 32-bit numbers, N and M, and two bit positions, i and j. Write a
     * method to set all bits between i and j in N equal to M (e.g., M becomes a substring of
     * N located at i and starting at j).
     * EXAMPLE:
     * Input: N = 1024 = 10000000000, M = 21 = 10101, i = 2, j = 6
     * Output: N = 10001010100
     */
    public static int updateBits(int n, int m, int i, int j) {
        int max = ~0; /* All 1’s */

        int left = max - ((1 << j) - 1);

        int right = (1  << i) - 1;

        int mask = left ^ right;

        // Clear i through j, then put m in there
        return (n & mask) | (m << i);
    }


}

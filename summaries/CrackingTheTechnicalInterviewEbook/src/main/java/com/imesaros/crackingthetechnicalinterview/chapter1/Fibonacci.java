package com.imesaros.crackingthetechnicalinterview.chapter1;

public class Fibonacci {

    /**
     * Write a method to generate the nth Fibonacci number.
     *
     * @param n
     * @return nth Fibonacci number.
     */
    public int generate1(int n) {
        if (n < 0) {
            return -1;
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return generate1(n - 1) + generate1(n - 2);
    }

    /**
     * Write a method to generate the nth Fibonacci number.
     *
     * @param n
     * @return nth Fibonacci number.
     */
    public int generate2(int n) {
        if (n < 0) {
            return -1;
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        Integer[] array = new Integer[n + 1];
        array[0] = 0;
        array[1] = 1;
        for (int i = 2; i <= n; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n];
    }

    /**
     * Write a method to generate the nth Fibonacci number.
     *
     * @param n
     * @return nth Fibonacci number.
     */
    public int generate3(int n) {
        if (n < 0) {
            return -1;
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int a = 0;
        int b = 1;
        for (int i = 2; i <= n; i++) {
            b = a + b;
            a = b - a;
        }
        return b;
    }
}

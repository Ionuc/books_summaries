package com.imesaros.crackingthetechnicalinterview.chapter1;

public class GreatestCommonDivisor {

    public int greatestCommonDivisor1(int a, int b) {
        if (a <= 0 || b <= 0) {
            return -1;
        }
        while (b > 0) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    public int greatestCommonDivisor2(int a, int b) {
        if (a <= 0 || b <= 0) {
            return -1;
        }
        return greatestCommonDiv2(a, b);
    }

    private int greatestCommonDiv2(int a, int b) {
        return b == 0 ? a : greatestCommonDiv2(b, a % b);
    }
}

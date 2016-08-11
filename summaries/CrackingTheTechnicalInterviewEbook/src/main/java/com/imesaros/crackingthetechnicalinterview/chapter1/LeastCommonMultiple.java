package com.imesaros.crackingthetechnicalinterview.chapter1;

public class LeastCommonMultiple {
    private GreatestCommonDivisor greatestCommonDivisor = new GreatestCommonDivisor();

    public int leastCommonMultiple1(int a, int b) {
        if (a <= 0 || b <= 0) {
            return -1;
        }
        return a * b / greatestCommonDivisor.greatestCommonDivisor1(a, b);
    }

    public int leastCommonMultiple2(int a, int b) {
        if (a <= 0 || b <= 0) {
            return -1;
        }
        int i = 1;
        while (true) {
            if ((a * i) % b == 0) {
                return a * i;
            }
            i++;
        }
    }
}

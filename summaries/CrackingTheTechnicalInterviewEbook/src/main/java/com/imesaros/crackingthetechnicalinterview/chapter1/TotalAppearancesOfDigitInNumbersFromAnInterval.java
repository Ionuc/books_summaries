package com.imesaros.crackingthetechnicalinterview.chapter1;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TotalAppearancesOfDigitInNumbersFromAnInterval {

    /**
     * Write a method to count the total appearances of digit k in the numbers between 0 and n
     *
     * @param n the maxim margin of interval [0, n]
     * @param k the digit intended to be found
     * @return the total number of appearances of digit k inside numbers from interval [0, n]
     */

    public int countNumberOfADigitFrom0ToN(int n, int k) {
        if (n <= 0) {
            return 0;
        }
        Integer[] digits = getDigits(n);
        Map<Integer, Integer> partialResults = createPartialResults(n, digits.length);

        return computeTotalDigits(digits, partialResults, k);
    }

    private Integer computeTotalDigits(Integer[] digits, Map<Integer, Integer> partialResults, int k) {
        Integer totalCount = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < k) {
                totalCount += digits[i] * computeFullDigitAppearance(i - 1);
            } else if (digits[i] > k) {
                totalCount += Math.max(digits[i] - 1, 0) * computeFullDigitAppearance(i - 1) + sumOfTensPower(i);
            } else {
                totalCount += digits[i] * computeFullDigitAppearance(i - 1);
                if (i > 0) {
                    totalCount += partialResults.get(i - 1) + 1;
                } else {
                    totalCount++;
                }
            }
        }
        return totalCount;
    }

    /**
     * Method used to calculate the sum of the power of 10, from 0 to j
     */
    private int sumOfTensPower(int j) {
        int count = 0;
        for (int i = 0; i <= j; i++) {
            count += (int) Math.pow(10, i);
        }
        return count;
    }

    /**
     * Method used to calculate all the appearances of p inside all numbers having from 1 to k digits.
     * The formula is f(k) = 9 * (f(0) + .. + f(k-1)) + 10^0 + .. + 10^(k-1)
     *
     * @param p is number of digits
     * @return all the appearances of p inside all numbers having from 1 to k digits
     */
    private Integer computeFullDigitAppearance(int p) {
        if (p < 0) {
            return 0;
        }
        if (p == 0) {
            return 1;
        }
        Integer[] array = new Integer[p + 1];
        array[0] = 1;
        for (int i = 1; i <= p; i++) {
            Integer count = 0;
            for (int j = 0; j < i; j++) {
                count += array[j];
            }
            array[i] = 9 * count + sumOfTensPower(i);
        }
        return array[p];
    }

    /**
     * Method used to transform a number n into an Array of its own digits.
     * For Example : 1234 => [4,3,2,1]
     */
    private Integer[] getDigits(int n) {
        List<Integer> list = new ArrayList<>();
        while (n > 0) {
            list.add(n % 10);
            n /= 10;
        }
        return list.toArray(new Integer[list.size()]);
    }

    /**
     * Method used to compute from a given n its relative partial results.
     * The map returned is containing the entries : (i, k) there i is an integer from [0, digitsNr-1]
     * and k is the result of taking the last (i + 1) digits from number n;
     * For example : n = 1234 => (2, 234), (1, 34) (0, 4)
     */
    private Map<Integer, Integer> createPartialResults(int n, int digitsNr) {
        Map<Integer, Integer> partialResults = new HashMap<>();
        for (int i = digitsNr - 1; i > 0; i--) {
            n %= Math.pow(10, digitsNr - 1);
            digitsNr--;
            partialResults.put(digitsNr - 1, n);

        }
        return partialResults;
    }
}

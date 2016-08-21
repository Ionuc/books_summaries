package com.imesaros.crackingthetechnicalinterview.part2.chapter5;

public class BitManipulation {

    /**
     * Problem 5.1
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

        int right = (1 << i) - 1;

        int mask = left ^ right;

        // Clear i through j, then put m in there
        return (n & mask) | (m << i);
    }

    /**
     * Problem 5.2
     * Given a (decimal - e.g. 3.72) number that is passed in as a string, print the binary representation.
     * If the number can not be represented accurately in binary, print “ERROR”
     */
    public static String createBinaryRepresentation(String sValue) {
        Float fValue = Float.parseFloat(sValue);
        Integer iValue = fValue.intValue();
        fValue -= iValue;

        String tails = "";
        if (fValue > 0) {
            String error = "ERROR";
            tails = binaryRepresentationForFloating(fValue, error);
            if (tails.equals(error)) {
                return error;
            }
        }
        String head = binaryRepresentationForInteger(iValue);
        return head + (tails.equals("") ? tails : "." + tails);
    }

    private static String binaryRepresentationForFloating(Float value, String error) {
        String result = "";
        int intValue;
        while (value != 0) {
            if (result.length() > 32) {
                return error;
            }
            value *= 2;
            intValue = value.intValue();
            result += intValue;
            value -= intValue;
        }
        return result;
    }

    private static String binaryRepresentationForInteger(Integer value) {
        String result = "";
        while (value != 0) {
            result = value % 2 + result;
            value /= 2;
        }
        return result;
    }

    /**
     * Problem 5.5
     * Write a function to determine the number of bits required to convert integer A to
     * integer B.
     * Input: 31, 14
     * Output: 2
     */
    public static int nrBitsToConvert(int from, int to) {
        int count = 0;
        int c = from ^ to;
        while (c != 0) {
            count += c & 1;
            c = c >> 1;
        }
        return count;
    }


    /**
     * Problem 5.6
     * Write a program to swap odd and even bits in an integer with as few instructions as
     * possible (e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, etc).
     */
    public static int swapOddWithEven(int nr) {
        return ((nr & 0xAA) >> 1) | ((nr & 0x55) << 1);
    }
}

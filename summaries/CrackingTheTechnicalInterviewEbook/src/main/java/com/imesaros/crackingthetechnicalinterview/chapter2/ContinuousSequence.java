package com.imesaros.crackingthetechnicalinterview.chapter2;

import java.util.List;

import static java.util.Collections.emptyList;

/**
 * You are given an array of integers (both positive and negative). Find the continuous
 * sequence with the largest sum. Return the sum.
 * EXAMPLE :
 * input: {2, -8, 3, -2, 4, -10}
 * output: 5 [ e.g., {3, -2, 4} ]
 */
public class ContinuousSequence {

    public static List<Integer> withLargestSum(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return emptyList();
        }

        int globalMaxim = list.get(0);
        int localMaxim = list.get(0);

        int startLocal = 0, endLocal = 0;
        int startGlobal = 0, endGlobal = 0;

        for (int i = 1; i < list.size(); i++) {
            if (localMaxim >= 0) {
                if ((list.get(i) >= 0) || (localMaxim + list.get(i) >= 0)) {
                    endLocal++;
                    localMaxim += list.get(endLocal);
                } else if (localMaxim + list.get(i) >= 0) {
                    endLocal++;
                    localMaxim += list.get(endLocal);
                } else {
                    startLocal = endLocal = i;
                    localMaxim = list.get(i);
                }
            } else {
                if (list.get(i) >= 0) {
                    startLocal = endLocal = i;
                    localMaxim = list.get(i);
                } else {
                    if (localMaxim < list.get(i)) {
                        startLocal = endLocal = i;
                        localMaxim = list.get(i);
                    }
                }
            }

            if (localMaxim > globalMaxim) {
                globalMaxim = localMaxim;
                startGlobal = startLocal;
                endGlobal = endLocal;
            }
        }
        return list.subList(startGlobal, endGlobal + 1);
    }
}

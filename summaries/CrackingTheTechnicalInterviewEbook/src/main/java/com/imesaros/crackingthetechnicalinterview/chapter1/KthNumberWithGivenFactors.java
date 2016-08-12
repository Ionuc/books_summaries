package com.imesaros.crackingthetechnicalinterview.chapter1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Arrays.asList;

/**
 * Problem : 8 Design an algorithm to find the kth number such that the only prime factors are 3, 5, and 7
 */
public class KthNumberWithGivenFactors {

    private List<Integer> factorsOf3 = new ArrayList<>(asList(3));
    private List<Integer> factorsOf5 = new ArrayList<>(asList(5));
    private List<Integer> factorsOf7 = new ArrayList<>(asList(7));

    private Consumer<Integer> consumeFromFactorsOf7 = (Integer min) -> {
        factorsOf7.add(7 * min);
        Collections.sort(factorsOf7);
    };

    private Consumer<Integer> consumeFromFactorsOf5 = (Integer min) -> {
        factorsOf5.add(5 * min);
        Collections.sort(factorsOf5);

        consumeFromFactorsOf7.accept(min);
    };

    private Consumer<Integer> consumeFromFactorsOf3 = (Integer min) -> {
        factorsOf3.add(3 * min);
        Collections.sort(factorsOf3);

        consumeFromFactorsOf5.accept(min);
    };


    public int findKthElement(int k) {
        if (k <= 0) {
            return 0;
        }

        int count = 0;
        int min = 0;
        while (count < k) {
            min = Integer.min(factorsOf3.get(0), factorsOf5.get(0));
            min = Integer.min(min, factorsOf7.get(0));

            if (factorsOf3.contains(min)) {
                factorsOf3.remove(0);
                consumeFromFactorsOf3.accept(min);
            }
            if (factorsOf5.contains(min)) {
                factorsOf5.remove(0);
                consumeFromFactorsOf5.accept(min);
            }
            if (factorsOf7.contains(min)) {
                factorsOf7.remove(0);
                consumeFromFactorsOf7.accept(min);
            }
            count++;
        }
        return min;
    }
}

package com.imesaros.crackingthetechnicalinterview.chapter1;

import java.util.*;
import java.util.function.Consumer;

import static java.util.Arrays.asList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * Problem : 8 Design an algorithm to find the kth number such that the only prime factors are 3, 5, and 7
 * The implementations is generalized for prime numbers p1, p2 , .. pn given inside list of primeFactors
 */
public class KthNumberWithGivenFactors {

    public int findKthElementWithFactors3And5And7(int k) {
        return findKthElementWithFactors(k, asList(3, 5, 7));
    }

    public int findKthElementWithFactors(int k, List<Integer> primeFactors) {
        if (k <= 0) {
            return 0;
        }
        List<Integer> primeOrderFactors = primeFactors.stream().sorted().collect(toList());

        Map<Integer, List<Integer>> factorsOf = primeOrderFactors.stream().collect(toMap(
                identity(), value -> new ArrayList<Integer>(asList(value))
        ));

        Map<Integer, Consumer<Integer>> consumers = new HashMap<>();
        for (int i = primeOrderFactors.size() - 1; i >= 0; i--) {
            if (i == primeOrderFactors.size() - 1) {
                consumers.put(primeOrderFactors.get(i), new CustomConsumer(factorsOf.get(primeOrderFactors.get(i)), primeOrderFactors.get(i), value -> {
                }));
            } else {
                consumers.put(primeOrderFactors.get(i), new CustomConsumer(factorsOf.get(primeOrderFactors.get(i)), primeOrderFactors.get(i), consumers.get(primeOrderFactors.get(i + 1))));
            }
        }

        int count = 0;
        int min = 0;
        while (count < k) {
            Map.Entry<Integer, Integer> pair = factorsOf.entrySet().stream()
                    .map(entry -> new AbstractMap.SimpleImmutableEntry<Integer, Integer>(entry.getKey(), entry.getValue().get(0)))
                    .min((o1, o2) -> o1.getValue().compareTo(o2.getValue())).get();
            min = pair.getValue();
            factorsOf.get(pair.getKey()).remove(0);
            consumers.get(pair.getKey()).accept(min);
            count++;
        }
        return min;

    }

    private class CustomConsumer implements Consumer<Integer> {

        private final List<Integer> factorsOf;
        private final Integer multiplier;
        private final Consumer<Integer> nextConsumer;

        private CustomConsumer(List<Integer> factorsOf, Integer multiplier, Consumer<Integer> nextConsumer) {
            this.factorsOf = factorsOf;
            this.multiplier = multiplier;
            this.nextConsumer = nextConsumer;
        }

        @Override
        public void accept(Integer value) {
            factorsOf.add(multiplier * value);
            Collections.sort(factorsOf);

            nextConsumer.accept(value);
        }
    }
}

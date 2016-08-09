package com.imesaros.thealgorithmdesignmanual.sorting.impl;

import com.imesaros.thealgorithmdesignmanual.sorting.SortStrategy;

import java.util.Comparator;

import static com.imesaros.thealgorithmdesignmanual.sorting.SortHelper.swap;

public class SelectionSort<T> implements SortStrategy<T> {

    private static final String NAME = "Selection Sort";

    @Override
    public T[] sort(T[] array, Comparator<T> comparator) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (comparator.compare(array[minIndex], array[j]) > 0) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
        return array;
    }

    @Override
    public String getName() {
        return NAME;
    }
}

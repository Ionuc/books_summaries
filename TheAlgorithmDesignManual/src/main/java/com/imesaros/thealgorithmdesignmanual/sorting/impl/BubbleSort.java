package com.imesaros.thealgorithmdesignmanual.sorting.impl;

import com.imesaros.thealgorithmdesignmanual.sorting.SortStrategy;

import java.util.Comparator;

import static com.imesaros.thealgorithmdesignmanual.sorting.SortHelper.swap;

public class BubbleSort<T> implements SortStrategy<T> {

    private static final String NAME = "Bubble sort";

    @Override
    public T[] sort(T[] array, Comparator<T> comparator) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (comparator.compare(array[i], array[j]) > 0) {
                    swap(array, i, j);
                }
            }
        }
        return array;
    }

    @Override
    public String getName() {
        return NAME;
    }
}

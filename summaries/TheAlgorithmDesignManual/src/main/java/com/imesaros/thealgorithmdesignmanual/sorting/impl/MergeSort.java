package com.imesaros.thealgorithmdesignmanual.sorting.impl;

import com.imesaros.thealgorithmdesignmanual.sorting.SortStrategy;

import java.util.Comparator;
import java.util.function.Function;

public class MergeSort<T> implements SortStrategy<T> {

    private Function<Integer, T[]> creator;

    public MergeSort(Function<Integer, T[]> creator) {
        this.creator = creator;
    }

    private static final String NAME = "Merge Sort";

    @Override
    public T[] sort(T[] array, Comparator<T> comparator) {
        sort(array, comparator, 0, array.length - 1);
        return array;
    }

    private void sort(T[] array, Comparator<T> comparator, int start, int end) {
        if (start >= end) {
            return;
        }
        int middle = (start + end) / 2;
        sort(array, comparator, start, middle);
        sort(array, comparator, middle + 1, end);
        merge(array, comparator, start, middle, end);
    }

    private void merge(T[] array, Comparator<T> comparator, int start, int middle, int end) {
        if (end - start < 1) {
            return;
        }

        int index1 = start;
        int index2 = middle + 1;
        int index = 0;

        T[] buffer = creator.apply(end - start + 1);
        while ((index1 <= middle) && (index2 <= end)) {
            if (comparator.compare(array[index1], array[index2]) < 0) {
                buffer[index++] = array[index1++];
            } else {
                buffer[index++] = array[index2++];
            }
        }
        while (index1 <= middle)
            buffer[index++] = array[index1++];
        while (index2 <= end)
            buffer[index++] = array[index2++];

        for (int i = 0; i <= end - start; i++) {
            array[i + start] = buffer[i];
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}

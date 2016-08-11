package com.imesaros.thealgorithmdesignmanual.sorting.impl;

import com.imesaros.thealgorithmdesignmanual.sorting.SortHelper;
import com.imesaros.thealgorithmdesignmanual.sorting.SortStrategy;

import java.util.Comparator;

import static com.imesaros.thealgorithmdesignmanual.sorting.SortHelper.swap;

public class QuickSort<T> implements SortStrategy<T> {

    private static final String NAME = "Quick Sort";

    @Override
    public T[] sort(T[] array, Comparator<T> comparator) {
        quickSort(array, comparator, 0, array.length-1);
        return array;
    }

    private void quickSort(T[] array, Comparator<T> comparator, int start, int end) {
        if (start >= end) {
            return;
        }
        int index = partition(array, comparator, start, end);
        quickSort(array, comparator, start, index - 1);
        quickSort(array, comparator, index + 1, end);
    }

    private int partition(T[] array, Comparator<T> comparator, int start, int end) {
        T pivot = array[end];
        int beginPosition = start;
        int endPosition = end - 1;
        while (beginPosition <= endPosition) {
            if (comparator.compare(array[beginPosition], pivot) <= 0) {
                beginPosition++;
            } else {
                swap(array, beginPosition, endPosition);
                endPosition --;
            }
        }
        swap(array, beginPosition, end);
        return beginPosition;
    }

    @Override
    public String getName() {
        return NAME;
    }
}

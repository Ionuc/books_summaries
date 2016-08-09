package com.imesaros.thealgorithmdesignmanual.sorting;

import java.util.Comparator;

public interface SortStrategy<T> {
    T[] sort(T[] array, Comparator<T> comparator);

    String getName();
}

package com.imesaros.thealgorithmdesignmanual.sorting.impl;

import com.imesaros.thealgorithmdesignmanual.sorting.SortStrategy;

import java.util.Comparator;
import java.util.function.Function;

import static com.imesaros.thealgorithmdesignmanual.sorting.SortHelper.swap;

public class HeapSort<T> implements SortStrategy<T> {

    private static final String NAME = "Heap Sort";
    private int heapSize = 0;

    private Function<Integer, T[]> creator;

    public HeapSort(Function<Integer, T[]> creator) {
        this.creator = creator;
    }

    @Override
    public T[] sort(T[] array, Comparator<T> comparator) {

        T[] heap = makeHeap(array, comparator);

        for (int i = 0; i < array.length; i++) {
            array[i] = min(heap, comparator);
        }
        return array;
    }

    private T[] makeHeap(T[] array, Comparator<T> comparator) {
        heapSize = array.length;
        T[] heap = creator.apply(heapSize);
        for (int i = 0; i < heapSize; i++) {
            heap[i] = array[i];
        }
        for (int i = heapSize - 1; i >= 0; i--) {
            bubbleDown(heap, comparator, i);
        }

        return heap;
    }

    private void bubbleDown(T[] heap, Comparator<T> comparator, int index) {
        int childNode = getChildIndex(index);
        if (childNode >= heapSize) {
            return;
        }
        int minIndex = index;
        int totalChild = heapSize - childNode == 1 ? 1 : 2;
        for (int i = 0; i < totalChild; i++) {
            if (comparator.compare(heap[minIndex], heap[childNode + i]) > 0) {
                minIndex = childNode + i;
            }
        }
        if (minIndex != index) {
            swap(heap, minIndex, index);
            bubbleDown(heap, comparator, minIndex);
        }
    }

    private T min(T[] heap, Comparator<T> comparator) {
        T min = heap[0];
        heap[0] = heap[heapSize - 1];
        heap[--heapSize] = null;
        bubbleDown(heap, comparator, 0);
        return min;
    }

    private int getChildIndex(int n) {
        return 2 * n + 1;
    }

    @Override
    public String getName() {
        return NAME;
    }
}

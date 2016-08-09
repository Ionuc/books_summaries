package com.imesaros.thealgorithmdesignmanual.sorting;

public class SortHelper {
    public static <T> void swap(T[] array, int index1, int index2) {
        if (array == null || array.length < index1 || array.length < index2) {
            return;
        }
        T aux = array[index1];
        array[index1] = array[index2];
        array[index2] = aux;
    }
}

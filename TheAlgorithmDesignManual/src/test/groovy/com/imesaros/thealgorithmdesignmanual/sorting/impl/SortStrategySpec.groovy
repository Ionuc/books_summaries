package com.imesaros.thealgorithmdesignmanual.sorting.impl

import spock.lang.Specification

class SortStrategySpec extends Specification {

    def 'should sort array'() {
        given:
        Integer[] array = [8, 7, 6, 5, 4, 3, 2, 1]
        Comparator<Integer> comparator = { a1, a2 -> a1.compareTo(a2) }

        when:
        Integer[] result = sortStrategy.sort(array, comparator)

        then:
        println(sortStrategy.getName())
        result.each { println(it) }
        println(sortStrategy.getName())
        for (int i = 0; i < result.length - 1; i++) {
            result[i] < result[i + 1]
        }

        where:
        sortStrategy << [
                new SelectionSort<>(),
                new InsertionSort<>(),
                new BubbleSort<>(),
                new MergeSort<>({ i -> new Integer[i] }),
                new HeapSort<>({ i -> new Integer[i] }),
                new QuickSort<>()
        ]
    }
}

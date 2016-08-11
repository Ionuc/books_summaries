package com.imesaros.thealgorithmdesignmanual.sorting.impl

import spock.lang.Specification

import java.util.stream.IntStream


class PerformanceSortStrategySpec extends Specification{

    def Integer[] array = createArray(100000);

    def createArray(int n)
    {
        IntStream.rangeClosed(0, n).toArray()
    }

    def 'should check performance time for different SortStrategy'(){
        given:
        Comparator<Integer> comparator =  { a1, a2 -> a1.compareTo(a2) }
        when:
        long startTime = System.currentTimeMillis()
        sortStrategy.sort(array,  comparator)
        long endTime = System.currentTimeMillis();

        then:
        println(sortStrategy.name)
        println(endTime - startTime)
        println(sortStrategy.name)

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

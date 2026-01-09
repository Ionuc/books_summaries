.. _java12_teeing_collector:

Teeing Collector
================

    - a new teeing collector was introduced in Java 12 as an addition to the Collectors class:


    .. code-block:: python
           :linenos:

            Collector<T, ?, R> teeing(Collector<? super T, ?, R1> downstream1,
                Collector<? super T, ?, R2> downstream2, BiFunction<? super R1, ? super R2, R> merger)



    - it is a composite of two downstream collectors
    - every element is processed by both downstream collectors
    - then their results are passed to the merge function and transformed into the final result.


    .. code-block:: python
           :linenos:

            // Example: counting an average from a set of numbers
            @Test
            public void givenSetOfNumbers_thenCalculateAverage() {
                double mean = Stream.of(1, 2, 3, 4, 5)
                    .collect(Collectors.teeing(Collectors.summingDouble(i -> i), 
                        Collectors.counting(), (sum, count) -> sum / count));
                assertEquals(3.0, mean);
            }



:ref:`Go Back <java12-developer-features-label>`.
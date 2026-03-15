.. _java-development-streams-api-label:

Stream Collectors
=================
- it was introduced with Java 8
- it provides the strategy to perform fold operations (repackaging elements to some data structures and applying some additional logic, concatenating them, etc.) on data elements held in a Stream instance

Stream.collect() Method
-----------------------
- The collect() method is one of Java 8’s Stream API terminal methods
- It allows us to perform mutable fold operations (repackaging elements to some data structures and applying some additional logic, concatenating them, etc.) on data elements held in a Stream instance
- The strategy for this operation is provided via the Collector interface implementation.


Collectors.toCollection()
-------------------------
- when using the toSet() and toList() collectors, we can’t make any assumptions about their implementations.
- If we want to use a custom implementation, we’ll need to use the toCollection() collector with a provided collection of our choice.

    .. code-block:: python
           :linenos:

           List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");
           List<String> result = givenList.stream().collect(toCollection(LinkedList::new))


Collectors.toList()
-------------------
- As the name implies, the main purpose of the toList() method is to collect all Stream elements into a List instance
- The important thing to remember is that we can’t assume any particular List implementation with this method.
- If we want to have more control over this, we can use toCollection() instead.


    .. code-block:: python
           :linenos:

           List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");
           List<String> result = givenList.stream().collect(toList());


Collectors.toSet()
------------------
- The toSet() collector can be used to collect all Stream elements in a Set instance.
- The important thing to remember is that we can’t assume any particular Set implementation with this method.
- If we want to have more control over this, we can use toCollection() instead.


    .. code-block:: python
           :linenos:

            List<String> listWithDuplicates = Arrays.asList("a", "bb", "c", "d", "bb");
            Set<String> result = listWithDuplicates.stream().collect(toSet());
            assertThat(result).hasSize(4);


Collectors.toMap()
------------------
- The toMap() collector can be used to collect Stream elements into a Map instance.
- To do so, we need to provide two functions: keyMapper() and valueMapper().
- Firstly, we’ll use :
    - keyMapper() to extract a Map key from a Stream element.
    - use valueMapper() to extract a value associated with a given key.

    .. code-block:: python
           :linenos:

            Map<String, Integer> result = givenList.stream().collect(toMap(Function.identity(), String::length))


- Contrary to toSet(), the toMap() method doesn’t silently filter duplicates, which is understandable because how would it figure out which value to pick for this key?
- toMap() doesn’t even evaluate whether the values are also equal. If it sees duplicate keys, it immediately throws an IllegalStateException.


    .. code-block:: python
           :linenos:

            List<String> listWithDuplicates = Arrays.asList("a", "bb", "c", "d", "bb");
            assertThatThrownBy(() -> {
                listWithDuplicates.stream().collect(toMap(Function.identity(), String::length));
            }).isInstanceOf(IllegalStateException.class);


- in such cases with key collision, we should use toMap() with another signature:
    - The third argument here is a BinaryOperator(), where we can specify how we want to handle collisions


    .. code-block:: python
           :linenos:

            Map<String, Integer> result = givenList.stream().collect(toMap(Function.identity(), String::length, (item, identicalItem) -> item));


Collectors.collectingAndThen
----------------------------
- CollectingAndThen() is a special collector that allows us to perform another action on a result straight after collecting ends.


    .. code-block:: python
           :linenos:

            // Let’s collect Stream elements to a List instance, and then convert the result into an ImmutableList instance
            List<String> result = givenList.stream().collect(collectingAndThen(toList(), ImmutableList::copyOf))


Collectors.joining()
--------------------
- The Joining() collector can be used for joining Stream<String> elements.


    .. code-block:: python
           :linenos:

            List<String> listWithDuplicates = Arrays.asList("a", "bb", "ccc", "dd");
            String result = givenList.stream().collect(joining()); // => "abbcccdd"


- you can use a different joiner:


    .. code-block:: python
           :linenos:

            List<String> listWithDuplicates = Arrays.asList("a", "bb", "ccc", "dd");
            String result = givenList.stream().collect(joining(" ")); // => "a bb ccc dd"


- you can also have a prefix & suffix in the result:


    .. code-block:: python
           :linenos:

            List<String> listWithDuplicates = Arrays.asList("a", "bb", "ccc", "dd");
            String result = givenList.stream().collect(joining(" ", "PRE-", "-POST")); // => "PRE-a bb ccc dd-POST"


Collectors.counting()
---------------------
- Counting() is a simple collector that allows for the counting of all Stream elements.


    .. code-block:: python
           :linenos:

            List<String> listWithDuplicates = Arrays.asList("a", "bb", "ccc", "dd");
            Long result = givenList.stream().collect(counting()); // => 4


Collectors.summarizingDouble/Long/Int()
---------------------------------------
- SummarizingDouble/Long/Int is a collector that returns a special class containing statistical information about numerical data in a Stream of extracted elements.
- We can obtain information about string lengths by doing:


    .. code-block:: python
           :linenos:

            DoubleSummaryStatistics result = givenList.stream().collect(summarizingDouble(String::length));

            assertThat(result.getAverage()).isEqualTo(2);
            assertThat(result.getCount()).isEqualTo(4);
            assertThat(result.getMax()).isEqualTo(3);
            assertThat(result.getMin()).isEqualTo(1);
            assertThat(result.getSum()).isEqualTo(8);


Collectors.averagingDouble/Long/Int()
-------------------------------------
- AveragingDouble/Long/Int is a collector that simply returns an average of extracted elements.


    .. code-block:: python
           :linenos:

            Double result = givenList.stream().collect(averagingDouble(String::length));


Collectors.summingDouble/Long/Int()
-----------------------------------
- SummingDouble/Long/Int is a collector that simply returns a sum of extracted elements.


    .. code-block:: python
           :linenos:

            Double result = givenList.stream().collect(summingDouble(String::length));


Collectors.maxBy/minBy
----------------------
- MaxBy() and MinBy() collectors return the biggest/smallest element of a Stream according to a provided Comparator instance.


    .. code-block:: python
           :linenos:

            Optional<String> result = givenList.stream().collect(maxBy(Comparator.naturalOrder()));


Collectors.groupingBy()
-----------------------
- We can group them by string length, and store the grouping results in Set instances:


    .. code-block:: python
           :linenos:

            Map<Integer, Set<String>> result = givenList.stream().collect(groupingBy(String::length, toSet()));

            assertThat(result)
            .containsEntry(1, newHashSet("a"))
            .containsEntry(2, newHashSet("bb", "dd"))
            .containsEntry(3, newHashSet("ccc"));


Collectors.partitioningBy()
---------------------------
- partitioningBy() is a specialized case of groupingBy() that accepts a Predicate instance, and then collects Stream elements into a Map instance that stores Boolean values as keys and collections as values.
    - Under the “true” key, we can find a collection of elements matching the given Predicate
    - under the “false” key, we can find a collection of elements not matching the given Predicate


    .. code-block:: python
           :linenos:

           Map<Boolean, List<String>> result = givenList.stream().collect(partitioningBy(s -> s.length() > 2))
           // => {false=["a", "bb", "dd"], true=["ccc"]}


Custom Collectors
-----------------
- If we want to write our own Collector implementation, we need to implement the Collector interface, and specify its three generic parameters:
    - T – the type of objects that will be available for collection
    - A – the type of a mutable accumulator object
    - R – the type of a final result


Java 10 improvements
--------------------
    - java.util.stream.Collectors has a new method toUnmodifiableList(), toUnmodifiableSet(), toUnmodifiableMap() to collect a Stream into an unmodifiable collection

    .. code-block:: python
           :linenos:

           @Test(expected = UnsupportedOperationException.class)
           public void whenModifyToUnmodifiableList_thenThrowsException() {
              List<Integer> evenList = someIntList.stream()
                    .filter(i -> i % 2 == 0)
                    .collect(Collectors.toUnmodifiableList());
              evenList.add(4);
           }


Java 12 improvements
--------------------
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


:ref:`Go Back <java-development-streams-api-label>`.
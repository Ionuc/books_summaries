.. _java9-stream-api:

Stream APi Improvements
=======================

    - A Stream is a sequence of elements and supports a set of aggregate operations on them easily
    - It supports those operations either in sequential or parallel way depends on our requirements
    - operations in stream can be done in lazy and parallel way
    - useful new methods were added to Stream api:
        - dropDown (default method)
        - takeWhile (default method)
        - iterate (static method)
        - ofNullable (static method)

Stream API takeWhile() method
-----------------------------
    - returns longest prefix elements which matches the Predicate condition
    - It behaves differently for Ordered and Unordered Streams

    .. code-block:: python
        :linenos:

        default Stream<T> takeWhile(Predicate<? super T> predicate)

    - if Stream is ordered:
        - takeWhile() returns the longest prefix which matches that Predicate
        - The resulted Stream contains only that prefix elements which matches that Predicate condition

    .. code-block:: python
        :linenos:

        jshell> Stream<Integer> stream = Stream.of(1,2,3,4,5,6,7,8,9,10)
        stream ==> java.util.stream.ReferencePipeline$Head@55d56113

        jshell> stream.takeWhile(x -> x < 4).forEach(a -> System.out.println(a))
        1
        2
        3

    - if Stream is unordered:
        - takeWhile() returns all prefixed elements until they match Predicate condition
        - When that Predicate returns false for first element, then it stops evaluation and returns that subset elements
        - That Predicate is evaluated until that returns false for first time

    .. code-block:: python
        :linenos:

        jshell> Stream<Integer> stream = Stream.of(1,2,4,5,3,6,7,8,9,10)
        stream ==> java.util.stream.ReferencePipeline$Head@55d56113

        jshell> stream.takeWhile(x -> x < 4).forEach(a -> System.out.println(a))
        1
        2


Stream API dropWhile Method
---------------------------
    - dropWhile() Method drops the longest prefix elements which matches the Predicate and returns the rest of elements
    - It behaves differently for Ordered and Unordered Streams

    .. code-block:: python
        :linenos:

        default Stream<T> dropWhile(Predicate<? super T> predicate)

    - if Stream is ordered:
        - dropWhile method drops the longest prefix elements which matches that Predicate and returns the rest of elements
        - The resulted Stream contains all elements except those prefixed elements which matches the Predicate condition

    .. code-block:: python
        :linenos:

        jshell> Stream<Integer> stream = Stream.of(1,2,3,4,5,6,7,8,9,10)
        stream ==> java.util.stream.ReferencePipeline$Head@55d56113

        jshell> stream.dropWhile(x -> x < 4).forEach(a -> System.out.println(a))
        4
        5
        6
        7
        8
        9
        10

    - if Stream is unordered:
        - dropWhile() first drops all prefixed elements until they match Predicate condition
        - When that Predicate returns false for first element, then it stops evaluation and returns the rest of subset
          elements into resulted Stream

    .. code-block:: python
        :linenos:

        jshell> Stream<Integer> stream = Stream.of(1,2,4,5,3,6,7,8,9,10)
        stream ==> java.util.stream.ReferencePipeline$Head@55d56113

        jshell> stream.dropWhile(x -> x < 4).forEach(a -> System.out.println(a))
        4
        5
        3
        6
        7
        8
        9
        10

:ref:`Go Back <java9-label>`.
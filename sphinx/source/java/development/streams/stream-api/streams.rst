.. _java-development-streams-api-streams-label:

Stream
======
- it was introduced with Java 8
- Once created, the instance will not modify its source,
- A Stream is a sequence of elements and supports a set of aggregate operations on them easily
- It supports those operations either in sequential or parallel way depends on our requirements
- operations in stream can be done in lazy and parallel way
- don’t leave an instantiated stream unconsumed, as that will lead to memory leaks.

Empty Stream
------------
- We should use the empty() method in case of the creation of an empty stream:
- We often use the empty() method upon creation to avoid returning null for streams with no element:


    .. code-block:: python
        :linenos:

        Stream<String> streamEmpty = Stream.empty();


Stream of Collection
--------------------
- We can also create a stream of any type of Collection (Collection, List, Set):


    .. code-block:: python
        :linenos:

        Collection<String> collection = Arrays.asList("a", "b", "c");
        Stream<String> streamOfCollection = collection.stream();


Stream of Array
---------------
- An array can also be the source of a stream:


    .. code-block:: python
        :linenos:

        Stream<String> streamOfArray = Stream.of("a", "b", "c");


- We can also create a stream out of an existing array or of part of an array:


    .. code-block:: python
        :linenos:

        String[] arr = new String[]{"a", "b", "c"};
        Stream<String> streamOfArrayFull = Arrays.stream(arr);
        Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3);


Stream.builder()
----------------
- When builder is used, the desired type should be additionally specified in the right part of the statement, otherwise the build() method will create an instance of the Stream<Object>:


    .. code-block:: python
        :linenos:

        Stream<String> streamBuilder = Stream.<String>builder().add("a").add("b").add("c").build();


Stream.generate()
-----------------
- The generate() method accepts a Supplier<T> for element generation
- As the resulting stream is infinite, the developer should specify the desired size, or the generate() method will work until it reaches the memory limit


    .. code-block:: python
        :linenos:

        Stream<String> streamGenerated = Stream.generate(() -> "element").limit(10);


Stream.iterate()
----------------
- Another way of creating an infinite stream is by using the iterate() method:
- The first element of the resulting stream is the first parameter of the iterate() method. When creating every following element, the specified function is applied to the previous element. In the example above the second element will be 42:


    .. code-block:: python
        :linenos:

        Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);


Stream of Primitives
--------------------
- Java 8 offers the possibility to create streams out of three primitive types: int, long and double: IntStream, LongStream, DoubleStream
- Using the new interfaces alleviates unnecessary auto-boxing, which allows for increased productivity:


    .. code-block:: python
        :linenos:

        IntStream intStream = IntStream.range(1, 3);
        LongStream longStream = LongStream.rangeClosed(1, 3);


- The range(int startInclusive, int endExclusive) method creates an ordered stream from the first parameter to the second parameter. It increments the value of subsequent elements with the step equal to 1. The result doesn’t include the last parameter, it is just an upper bound of the sequence.
- The rangeClosed(int startInclusive, int endInclusive) method does the same thing with only one difference, the second element is included. We can use these two methods to generate any of the three types of streams of primitives.

Stream of String
----------------
- We can also use String as a source for creating a stream with the help of the chars() method of the String class
- Since there is no interface for CharStream in JDK, we use the IntStream to represent a stream of chars instead.


    .. code-block:: python
        :linenos:

        IntStream streamOfChars = "abc".chars();
        Stream<String> streamOfString = Pattern.compile(", ").splitAsStream("a, b, c");


Stream of File
--------------
- Java NIO class Files allows us to generate a Stream<String> of a text file through the lines() method.
- Every line of the text becomes an element of the stream:


    .. code-block:: python
        :linenos:

        Path path = Paths.get("C:\\file.txt");
        Stream<String> streamOfStrings = Files.lines(path);
        Stream<String> streamWithCharset = Files.lines(path, Charset.forName("UTF-8"));


Referencing a Stream
--------------------
- We can instantiate a stream, and have an accessible reference to it, as long as only intermediate operations are called
- Executing a terminal operation makes a stream inaccessible.
-  Java 8 streams can’t be reused.


    .. code-block:: python
        :linenos:

        Stream<String> stream = Stream.of("a", "b", "c").filter(element -> element.contains("b"));
        Optional<String> anyElement = stream.findAny();
        Optional<String> firstElement = stream.findFirst(); // => will throw IllegalStateException


Stream Pipeline
---------------
- o perform a sequence of operations over the elements of the data source and aggregate their results, we need three parts: 
    - the source
    - intermediate operation(s)
    - a terminal operation.

- Intermediate operations return a new modified stream
- If we need more than one modification, we can chain intermediate operations.
- A stream by itself is worthless; the user is interested in the result of the terminal operation, which can be a value of some type or an action applied to every element of the stream
- We can only use one terminal operation per stream.
- The correct and most convenient way to use streams is by a stream pipeline, which is a chain of the stream source, intermediate operations, and a terminal operation:


    .. code-block:: python
        :linenos:

        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        long size = list.stream().skip(1)
          .map(element -> element.substring(0, 3)).sorted().count();


Lazy Invocation
---------------
- Intermediate operations are lazy, this means that they will be invoked only if it is necessary for the terminal operation execution.

Order of Execution
------------------
- From the performance point of view, the right order is one of the most important aspects of chaining operations in the stream pipeline
- This brings us to the following rule: intermediate operations which reduce the size of the stream should be placed before operations which are applying to each element.
- So we need to keep methods such as skip(), filter(), and distinct() at the top of our stream pipeline.


    .. code-block:: python
        :linenos:

        long size = list.stream().map(element -> {
            wasCalled();
            return element.substring(0, 3);
        }).skip(2).count(); // => map() will be called 3 times

        long size = list.stream().skip(2).map(element -> {
            wasCalled();
            return element.substring(0, 3);
        }).count(); // => will call map() only 2 times


Stream Reduction
----------------
- The API has many terminal operations which aggregate a stream to a type or to a primitive: count(), max(), min(), and sum(). 
- However, these operations work according to the predefined implementation.
- So what if a developer needs to customize a Stream’s reduction mechanism? There are two methods which allow us to do this:
    - the reduce()
    - the collect()

- The reduce() Method:
    - There are three variations of this method, which differ by their signatures and returning types. They can have the following parameters:
        - identity – the initial value for an accumulator, or a default value if a stream is empty and there is nothing to accumulate
        - accumulator – a function which specifies the logic of the aggregation of elements. As the accumulator creates a new value for every step of reducing, the quantity of new values equals the stream’s size and only the last value is useful. This is not very good for the performance.
        - combiner – a function which aggregates the results of the accumulator. We only call combiner in a parallel mode to reduce the results of accumulators from different threads.


    .. code-block:: python
        :linenos:

        // reduce with one parameter
        OptionalInt reduced = IntStream.range(1, 4).reduce((a, b) -> a + b); // => reduced = 6 (1 + 2 + 3)


    .. code-block:: python
        :linenos:

        // reduce with 2 parameter
        int reducedTwoParams = IntStream.range(1, 4).reduce(10, (a, b) -> a + b); => reducedTwoParams = 16 (10 + 1 + 2 + 3)


    - in order to use the combiner, the stream must pe set in parallel mode, otherwhise the combiner will not be called


    .. code-block:: python
        :linenos:

        // reduce with 3 parameter
        int reducedParams = Stream.of(1, 2, 3)
          .reduce(10, (a, b) -> a + b, (a, b) -> {
             log.info("combiner was called");
             return a + b;
          }); // => reducedThreeParams = 16 (10 + 1 + 2 + 3)

        int reducedParallel = Stream.of(1, 2, 3).parallelStream()
          .reduce(10, (a, b) -> a + b, (a, b) -> {
             log.info("combiner was called");
             return a + b;
          }); // => reducedThreeParams = 36 ((10 + 1) + (10 + 2) + (10 + 3)


    - in case the combiner is called the algorith will be:
        - the accumulator ran three times by adding every element of the stream to identity. 
        - These actions are being done in parallel. As a result, they have (10 + 1 = 11; 10 + 2 = 12; 10 + 3 = 13;).
        - Now combiner can merge these three results. It needs two iterations for that (12 + 13 = 25; 25 + 11 = 36).

- The collect() Method():
    - It accepts an argument of the type Collector, which specifies the mechanism of reduction.
    - There are already created, predefined collectors for most common operations. They can be accessed with the help of the Collectors type.
    - more details about collectors can be found on the Collectors page


    .. code-block:: python
        :linenos:

        List<String> collectorCollection = productList.stream().map(Product::getName).collect(Collectors.toList());
        String listToString = productList.stream().map(Product::getName).collect(Collectors.joining(", ", "[", "]"));


Parallel Streams
----------------
- Java 8 introduced a way of accomplishing parallelism in a functional style.
- The API allows us to create parallel streams, which perform operations in a parallel mode
- When the source of a stream is a Collection or an array, it can be achieved with the help of the parallelStream() method:


    .. code-block:: python
        :linenos:

        Stream<Product> streamOfCollection = productList.parallelStream();
        boolean isParallel = streamOfCollection.isParallel();
        boolean bigPrice = streamOfCollection
          .map(product -> product.getPrice() * 12)
          .anyMatch(price -> price > 200);


- If the source of a stream is something other than a Collection or an array, the parallel() method should be used:


    .. code-block:: python
        :linenos:

        IntStream intStreamParallel = IntStream.range(1, 150).parallel();
        boolean isParallel = intStreamParallel.isParallel();


- Stream API automatically uses the ForkJoin framework to execute operations in parallel. 
- By default, the common thread pool will be used and there is no way (at least for now) to assign some custom thread pool to it.
- When using streams in parallel mode, avoid blocking operations
- It is also best to use parallel mode when tasks need a similar amount of time to execute. If one task lasts much longer than the other, it can slow down the complete app’s workflow.
- The stream in parallel mode can be converted back to the sequential mode by using the sequential() method:


    .. code-block:: python
        :linenos:

        IntStream intStreamSequential = intStreamParallel.sequential();
        boolean isParallel = intStreamSequential.isParallel();


Java 9 improvements
-------------------
- useful new methods were added to Stream api:
    - dropWhile (default method)
    - takeWhile (default method)
    - iterate (static method)
    - ofNullable (static method)

- Stream API takeWhile() method
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


- Stream API dropWhile Method
    - dropWhile() Method drops the longest prefix elements which matches the Predicate and returns the rest of elements
    - It behaves differently for Ordered and Unordered Streams
    - dropWhile() excludes all elements up to, but not including, the first element that fails the predicate

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

- Enhanced iterate() Method:
    - Java 9 adds a variant of the iterate() method that allows us to specify a condition under which the stream will stop generating elements, effectively creating a finite stream.
    - in example above, it will be generated numbers from 0 to 9, directly integrating the stopping condition within the iterate() method


    .. code-block:: python
        :linenos:

        Stream.iterate(0, i -> i < 10, i -> i + 1).forEach(System.out::println);


- ofNullable() for Optional Elements
    - Often, we may need to create a stream with a single element that might be null.
    - it will by returning an empty stream if the provided element is null, avoiding the need for complex conditional logic:


    .. code-block:: python
        :linenos:

        collection.stream()
          .flatMap(s -> Stream.ofNullable(map.get(s)))
          .collect(Collectors.toList());


Java 16 Improvements
--------------------
- The aim is to reduce the boilerplate with some commonly used Stream collectors, such as Collectors.toList and Collectors.toSet:



    .. code-block:: python
        :linenos:

        List<String> integersAsString = Arrays.asList("1", "2", "3");
        List<Integer> ints = integersAsString.stream().map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> intsEquivalent = integersAsString.stream().map(Integer::parseInt).toList();


:ref:`Go Back <java-development-streams-api-label>`.
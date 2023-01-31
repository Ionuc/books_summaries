.. _java10_performance_improvements:

Performance Improvements
========================

1) Parallet Full GC for G1
---------------------------

    - starting with Java 9, G1 garbage collector is the default one. But this collector, for full GC, is using a single thread for
      market-sweep-compact algorithm

    - in Java 10, full GC for G1 is done in parallel, reducing the stop-the-word time.

2) Application Class-Data Sharing
---------------------------------

    - Class-Data sharing introduced in Java 5 is allowing a set of classes to be pre-processed into a shared achive file
      that can be memory-mapped at runtime, reducing start time, which can also reduce memory footprint when multiple
      JVMs share the same file

    - CDS only allows the bootstrap classloader, limiting this feature to system classes only.
    - Application CDS extends CDS and allows the build-in system class loader and custom class loaders to load archive classes.
      This makes it possible to use the feature for application classes 

    .. code-block:: python
           :linenos:

            @Test
            public void whenListContainsInteger_OrElseThrowReturnsInteger() {
                Integer firstEven = someIntList.stream()
                  .filter(i -> i % 2 == 0)
                  .findFirst()
                  .orElseThrow();
                is(firstEven).equals(Integer.valueOf(2));
            }


:ref:`Go Back <java10-label>`.
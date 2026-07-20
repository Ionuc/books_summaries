.. _java-development-data-structure-collection-label:

Collection Interface
====================

- Collection vs Array:
    - collection:
        - dynamic zie
        - altorithms for data manipulations
        - single API
        - can work only with reference types
        - relatively lower performance
    - array:
        - fixed size
        - no any nehavior
        - no single API
        - can work with reference and primitive types
        - higher performance

- Collection methods:
    - size()
    - isEmpty()
    - contains(Object o)
    - toArray()
    - toArray(T[] arr)
    - add(E element)
    - remove(Object o)
    - containsAll(Collection c)
    - addAll(Collection c)
    - removeAll(Collection c)
    - removeIf(Predicate filter)
    - retainAll(Collection c)
    - clear()
    - equals(Object o)
    - hasCode()
    - iterator()
    - stream()

Java 11 improvement
-------------------

- new method was added to Collection interface to make easier to create a collection from a list

    .. code-block:: python
           :linenos:

            List sampleList = Arrays.asList("Java", "Kotlin");
            String[] sampleArray = sampleList.toArray(String[]::new);
            assertThat(sampleArray).containsExactly("Java", "Kotlin");

Java 21 improvements
--------------------
- it was introduced the SequenceCollection, SequenceSet, SequenceMap
- why:
    - no unified interface for ordered/sequence-aeare collctions
    - common tasks required boilerplate (ex: first/last, reverse)
    - inconsistent API
- benefist:
    - unified contract with methods:
        - first()
        - last()
        - addFirst()
        - addLast()
        - reverse()
    - order is not a first-class concept in collections framework

:ref:`Go Back <java-development-data-structures-label>`.
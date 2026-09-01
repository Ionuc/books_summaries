.. _java-development-data-structure-collection-label:

Collection Interface
====================

- Collection vs Array:
    - collection:
        - dynamic size
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

- new method was added to Collection interface to make easier to create an array from a list
    - it has the generic InFunction argument which will create the array having the specified lenght and type
    - no need to cast manually the result


    .. code-block:: python
           :linenos:

            List<String> stringList = new ArrayList<>();
            stringList.add("Java");
            stringList.add("Python");
            stringList.add("C++");
            
            String[] stringArray = stringList.toArray(size -> new String[size]);
            stringArray = stringList.toArray(String[]::new);


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
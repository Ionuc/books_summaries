.. _java-development-data-structure-map:

Map Data Structures
====================
    - a Map cares about unique identifiers.
    - you map a unique key to a specific object.
    - like Sets, Maps rely on equals() method
    - views over Map which will modify the map itself:
        - keySet() -> can only remove elements from Set result
        - values() -> can only remove elements from the Collection result
        - entrySet()
    - Sorted and Navigable maps:
        - provides a ordering for keys
        - provides methods like : firstKey(), lastKey()
        - provides views like tailMap(E fromKey), headMap(E toKey), subMap(K fromKey, K toKey)
        - when the Map is created, it needs to provide a Comparable Key, or a Comparator<K>
    - HashMap:
        - good general purpose implementation
        - is unsorted and unordered map
        - allow one null key, and multiple null values
        - uses hashcode() & equals() methods, so breaking the hashcode() contract will make HashMap working wrongly
        - mantains an aray of buckets
        - buckets are linked lists to accommodate collisions
        - buckets can be trees, if the linkedlist has more elements than a threshold 
        - the number of buckets can increased of a threshold is reached
    - Hashtable:
        - is synchronized comparing to HashTable
        - doesn’t let you have anything related to null
    - LinkedHashMap:
        - based upon HashMap
        - like LinkedHashSet, this collection maintains order of insertion, or access
        - you can expect faster iteration with LinkedHashMap
        - Slower then HashMap for insertion and deletion
        - helpful for implementing Caches
    - TreeMap:
        - is implemented using red-black tree
        - implements Navigable and Sorted
        - Uses comparable / comparator to define the order
        - lets you define a custom Comparator
    - WeakHashMap
        - weak references keys
        - key can be removed by GC when is unreachable
        - used as cache
    - EnumMap
        - use if keys are enums
        - are faster than other maps
        - implementation based upon bitsets


    .. image:: ../../../images/java/development/data-structures/maps-complexities.png
        :align: center


Immutable Map and Map.Entry with Java 9
---------------------------------------

    - above are examples how to create a new Map:

    .. code-block:: python
        :linenos:

        // how to create am empty immutable map
        // until java 8
        Map<Integer,String> emptyMap = new HashMap<>();
        Map<Integer,String> immutableEmptyMap = Collections.unmodifiableMap(emptyMap);

        //with java 9
        jshell> Map<Integer,String> emptyImmutableMap = Map.of()
        emptyImmutableMap ==> {}


    .. code-block:: python
        :linenos:

        // how to create an immutable map with values
        // until java8                
        Map<Integer,String> nonemptyMap = new HashMap<>();
        nonemptyMap.put(1,"one")
        nonemptyMap.put(2,"two")
        nonemptyMap.put(3,"three")
        Map<Integer,String> immutableNonEmptyMap = Collections.unmodifiableMap(nonemptyMap);

        //with java 9
        jshell> Map<Integer,String> nonemptyImmutableMap = Map.of(1, "one", 2, "two", 3, "three")
        nonemptyImmutableMap ==> {2=two, 3=three, 1=one}

    - you can create a maps from and array of entries:

    .. code-block:: python
        :linenos:

    jshell> Map<Integer,String> emptyImmutableMap = Map.ofEntries()
    emptyImmutableMap ==> {}

    import static java.util.Map.entry
    jshell> Map<Integer,String> emptyImmutableMap = Map.ofEntries(entry(1,"one"), entry(2,"two"), entry(3,"three"))
    emptyImmutableMap ==> {1=one, 2=two, 3=three}

    - above are examples of how to create a new Map.Entry:
        - the new methods needs the key and the corresponding value

    .. code-block:: python
        :linenos:

        Map.Entry<Integer,String> immutableMapEntry1 = Map.entry(1,"one")


:ref:`Go Back <java-development-data-structures-label>`.
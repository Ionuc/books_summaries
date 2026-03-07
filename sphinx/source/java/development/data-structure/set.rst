.. _java-development-data-structure-set:

Set Data Structures
====================
    - A set cares about uniqueness.
    - It doesn’t allow duplicates.
    - It is using equals() method to determine if two objects are equals.
    - The object inserted must overwrite the hashCode method. If not Object.hashCode() method is used
    - HashSet:
        - is unsorted and unordered  Set
        - based upon HashMap
        - it used hashcode of the object being inserted
        - use this class when you want a collections with no duplicated and don’t care about the order
    - LinkedHashSet:
        - is an ordered version of HashSet that maintains a double linked List across all elements
        - the order is the one on which the elements were inserted
    - TreeSet:
        - is one two sorted collections ( the other one being TreeMap )
        - based upon TreeMap
        - uses a Binary Tree with a required sort order
        - it uses a Red-Black tree structure
        - either you provide a Comparator in the constructor, either the elements implements Comparable
        - you can set a Comparator to the constructor of TreeSet used for determining the order
    - EnumSet:
        - specialized implementation for enums
        - uses a bitset based upon the oridinal of the enum
        - All of the elements in an enum set must come from a single enum type
        - are represented internally as bit vectors.  
        - This representation is extremely compact and efficient


    .. image:: ../../../images/java/development/data-structures/hashset-vs-treeset.png
        :align: center


    - SortedSet and NavigableSet:
        - a Collection with distinct elements with an order
        - SortedSet:
            - has a reference to the first and last element
            - provides new methods: tailSet(E fromElement), headSet(E toElement), subSet(E fromElement, E toElement)
            - returned value is a subset views, modifying it will modify the original SortedSet
            - fromElement is inclusive, toElement is exclusiv
        - NavigableSet:
            - extends SortedSet
            - provides ways to move through the order

Immutable Sets with Java 9
--------------------------

    - above are examples how to create a new Set:
    .. code-block:: python
        :linenos:

        // how to create am empty immutable set
        // until java 8
        Set<String> emptySet = new HashSet<>();
        Set<String> immutableSet = Collections.unmodifiableSet(emptySet);

        //with java 9
        Set<String> immutableSet = Set.of();

    .. code-block:: python
        :linenos:

        // how to create an immutable set with values
        // until java8        
        Set<String> nonemptySet = new HashSet<>();
        nonemptySet.add("one");
        nonemptySet.add("two");
        nonemptySet.add("three");
        Set<String> immutableSet = Collections.unmodifiableSet(nonemptySet);

        //with java 9
        Set<String> immutableSet = Set.of("one","two","three");

    - you can also create a set from an array:

    .. code-block:: python
        :linenos:

        String[] nameArr =  { "one", "two", "three"};
        Set<String[]> set= Set.<String[]>of(nameArr);

    - the characteristics of immutable set are the same as immutable lists

:ref:`Go Back <java-development-data-structures-label>`.
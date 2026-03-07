.. _java-development-data-structures-label:

Java Data structures
====================

    - Collections came in four basic flavors:
        - Lists -> List of things
        - Sets -> Unique things
        - Maps -> thinks with a  unique ID
        - Queue -> Things arranged by the order in which they are to be processed 

Ordered vs Sorted
-----------------
    - ordered:
        - When a collection is ordered, it means you can iterate through the collection in a specific order.
          For example, ArrayList keeps the order established by the element’s index position. LinkedHashSet keeps the order established by insertion
    - sorted:
        - A sorted collection means that the order in the collection is determined according to some rules.
          A sort order has nothing to do with when an object was added to the collection.Sorting is done using the properties of the objects themselve.
          You can put object inside a sorted collection and that collection will figure out what order to put them in.
          For a collection of String, the natural order is alphabetical.
          For Integer is by numeric value, for any other object ( like Foo ), the developer should provide an interface ( Comparable or Comparator )
    - An implementation class can be unsorted and unordered, ordered but unsorted, or both ordered and sorted.
      But an implementation can never be sorted but unordered because sorting is a specific type of ordering


Converting Arrays to Lists to Arrays
------------------------------------
    - The Arrays.asList() copies an array into a List. It is returning a fixed-size list backed by the specified array.
      When you use the asList() method, the array and the List become joined at the hip. When you update one of them, the other is updated automatically

Copy-on-Write Collections
--------------------------
    - is a List implementation that can be used concurrently without using traditional synchronization semantics
    - will never modify its internal array of data
    - any mutating operations on the List will cause a new modified copy of the array to be created which will be replace the original read-only array
    - a thread which is looping through the elements in this collection must keep a reference to the same unchanging elements throughout the duration of the loop.
      This is achieved with the use of Iteration. 

Concurrent Collections
-----------------------
    - Examples:
        - ConcurrentHashMap
        - ConcurrentLinkedDeque
        - ConcurrentLinkedQueue
        - ConcurrentSkipListMap
        - ConcurrentSkipListSet
    - These concurrent collections are not using the mechanism of copying its internal array
    - But the Iterator for a concurrent collection is weakly consistent. This mean when you iterate through the elements,
      other thread can add / remove the existing iterator, resulting wrong size



.. toctree::
    :maxdepth: 2
    :caption: Contents:

    list.rst
    set.rst
    map.rst
    queue.rst
    data-structures.rst

:ref:`Go Back <java-development-label>`.
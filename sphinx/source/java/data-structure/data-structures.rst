.. _data-structures:

Java Data Structures
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


List
----
    - A List cares about the index
    - One thing that List has that non-list don’t have is a set of methods related to the index
    - All implementations are ordered by index position
    - subList(start index, end index) -> will create the corresponding list with elements between those
      indexes. Adding & removing elements from that sublist will impact the original list

    - ArrayList:
        - is based upon array
        - it gives fast iteration and fast random access
        - it is an ordered collection ( by index ) but not sorted
        - Is bad when you do insertions and deletions
        - adding & removing at a index will shuffel all remaining elements
        - adding might double the size of the array
        - more CPU Cache sympathetic
    - Vector:
        - Vector and Hashtable were the two original collections
        - a Vector is basically the same as an ArrayList, but vector’s methods are synchronized for thread safety
        - Vector is the only class other than ArrayList to implement RandomAccess
    - LinkedList:
        - is ordered by index position, like ArrayList, except that the elements are doubly linked to one another
        - each element is a node having the value and the reference to the next and previous node
        - has an Head and a Tail which are updated
        - the iteration is slower than ArrayList, but the insertion and deletion are faster

    .. image:: ../../images/java/data-structures/arraylist-vs-linkedlist.png
        :align: center


Set
---
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

    .. image:: ../../images/java/data-structures/hashset-vs-treeset.png
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

Map
---
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

    .. image:: ../../images/java/data-structures/maps-complexities.png
        :align: center


Queue Interface
---------------
    - a Queue is designed to hold a list of “to-dos”, or things to be processed in some way.
    - Queues are order by FIFO
        - adding elements:
            - offer(E element) -> returns false if the queue is full
            - add(E exception) -> thrown exception if the queue is full
        - Removing element:
            - remove() -> throws Exception when empty
            - pool() -> returns null when empty
        - Read wihtout removing()
            - element() -> throws Exception when empty
            - peek() -> returns null for last element

    .. code-block:: python
       :linenos:

       Queue<Enquire> enquires = new ArrayDeque<>();
       Enquire enquire;
       while((enquire = enquires.poll()) != null ){
           // do something with enquire
       }

    .. code-block:: python
       :linenos:

       Queue<Enquire> enquires = new ArrayDeque<>();
       while(!enquires.isEmpty())
           Enquire enquire = enquires.remove();
           // do something with enquire
       }

    - PriorityQueue:
        - is a Queue where the order of elements come out is defined by priority
        - priority just defines ordering
        - elements are ordered either by natural ordering, or according to a Comparator.

    - Stacks:
        - are LIFO : Last In, First Out
        - java.utils.Stack is deprecated as all its methods are synchronized, while working with stack normally they don't care about concurrency
    - Double Ended Queues
        - has 2 ends
        - you can add and remove from both ends (from head or tail)
        - it can be used as a Queue (add to head, remove from tail) or Stack (add to head, remove from head)
        - it extends Queue interface, so all it has all methods from Queue
        - it adds methods to work with first and last (offerFirst(), offerLast(), addFirst(), addLast(), ...)
        - provides also push() & pop() methods to work as a Stack (you don't have to care which end you pick if you use it as a Stack)

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

TODO
-----
    - Read about Blocking Queues / Bounded Queues, Special-purpose Queues, LinkedTransferQueue

:ref:`Go Back <java-label>`.
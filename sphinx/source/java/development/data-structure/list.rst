.. _java-development-data-structure-list:

List Data Structures
====================
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


    .. image:: ../../../images/java/development/data-structures/arraylist-vs-linkedlist.png
        :align: center


Java 9 improvements
-------------------

    - above are examples how to create a new Immutable List:

    .. code-block:: python
        :linenos:

        // how to create am empty immutable list
        // until java 8
        List<String> emptyList = new ArrayList<>();
        List<String> immutableList = Collections.unmodifiableList(emptyList);

        //with java 9
        List immutableList = List.of();

    .. code-block:: python
        :linenos:

        // how to create an immutable list with values
        // until java8
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        List<String> immutableList = Collections.unmodifiableList(list);

        //with java 9
        List immutableList = List.of("one","two","three");

    .. code-block:: python
        :linenos:
     
        jshell> Map emptyImmutableMap = Map.of()
        emptyImmutableMap ==> {}

    .. code-block:: python
        :linenos:
        
        jshell> Map nonemptyImmutableMap = Map.of(1, "one", 2, "two", 3, "three")
        nonemptyImmutableMap ==> {2=two, 3=three, 1=one}

    - Characteristics of immutable lists
        - they are immutable
        - we cannot add, modify and delete their elements
        - if we try to perform add/delete/update operations on them => UnsupportedOpperationException
        - they don't allow null element => will result in NullPointerException
        - they are serializable of all elements are serializable

Java 10 improvements
--------------------
    - copyOf() was introuced to create a unmodifiable copy of a List


    .. code-block:: python
        :linenos:
        
        @Test(expected = UnsupportedOperationException.class)
        public void whenModifyCopyOfList_thenThrowsException() {
            List<Integer> copyList = List.copyOf(someIntList);
            copyList.add(4);
        }



:ref:`Go Back <java-development-data-structures-label>`.
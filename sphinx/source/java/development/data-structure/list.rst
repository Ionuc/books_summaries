.. _java-development-data-structure-list:

List Data Structures
====================
- A List cares about the index
- One thing that List has that non-list don’t have is a set of methods related to the index
- All implementations are ordered by index position
- list implementations may contain duplicates
- methods:
    - get(int index)
    - set(int index)
    - add(int index, T element)
    - indexOf(Object o)
    - lastIndexOf(Object o)
    - listIterator(int index)
    - subList(int fromIndex, int toIndex)
    - replaceAll(UnaryOpertor operator)
    - sort(Comparator c)
- posibility to create sub-lists:
    - subList(start index, end index) -> will create the corresponding list with elements between those indexes. Adding & removing elements from that sublist will impact the original list
- sorting based on Comparator


    .. image:: ../../../images/java/development/data-structures/list-hierarchy.png
        :align: center


Implementations
---------------

- ArrayList:
    - is based upon array wth dynamic size
    - it gives fast iteration and fast random access
    - it is an ordered collection ( by index ) but not sorted
    - Is bad when you do insertions and deletions
    - adding & removing at a index will shuffel all remaining elements
    - adding might double the size of the array
    - more CPU Cache sympathetic
    - when adding new elements, the internal array might be recreated with a higher capacity and all elements to be copied to the new array
    - when removing element, all elements after the current object will be shifted with previous index, but it will keep the current capacity, even if it might be smaller
    - method trimToSize() is reducing the current capacity and copy all current elements to the new internal array
    - it is recommended to set bigger capacity from beginning in case  you might know the size of the list
    - implement RandomAccess interface

- Vector:
    - Vector and Hashtable were the two original collections
    - a Vector is basically the same as an ArrayList, same mechansm of storing data, but vector’s methods are synchronized for thread safety
    - Vector is the only class other than ArrayList to implement RandomAccess (which is a marker interface)

- CopyOnWriteArrayList:
    - is a thread-safe version of ArrayList
    - has a higher performance comparing to Vector

- LinkedList:
    - is a double linked list: has pointer to the first and last element
    - is ordered by index position, like ArrayList, except that the elements are doubly linked to one another
    - each element is a node having the value and the reference to the next and previous node
    - has an Head and a Tail which are updated
    - the iteration is slower than ArrayList, but the insertion and deletion are faster
    - implement Deque interface which extends Queue interface

- Stack:
    - extends Vector
    - methods added to Stack are almost the same as of a Queue
    - because of backward compatibility, Stack was not changed to implement Queue, but let to extends Vector
    - implement LIFO (Last In First Out)


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

Java 21 improvements
--------------------
- was introduced interface SequenceCollection
- existig implementation: LinkedList, ArrayList, List.of()
- read-only operation works well: first(), last(), reverse()
- write operation (addFirst(), addLast()) might throw UnsupportedOperationException based on the implementation itself:
    - List.of() is creting an unmodifield collection => will throw exception when calling addFirst() or addLast()


    .. code-block:: python
        :linenos:
        
        // === BEFORE JEP 431: working with List and order ===
        public static void demoWithListBeforeJEP431() {
            System.out.println("\n--- List BEFORE JEP 431 ---");

            List<String> list = new LinkedList<>();
            list.add("B");
            list.add(0, "A"); // Add to beginning
            list.add("C");

            System.out.println("First element: " + list.get(0));
            System.out.println("Last element: " + list.get(list.size() - 1));

            // Reverse manually
            List<String> reversed = new ArrayList<>(list);
            Collections.reverse(reversed);
            System.out.println("Reversed list: " + reversed);
        }

        // === AFTER JEP 431: cleaner access with SequencedCollection ===
        public static void demoWithListAfterJEP431() {
            System.out.println("\n--- List AFTER JEP 431 (SequencedCollection) ---");

            SequencedCollection<String> list = new LinkedList<>();
            list.addFirst("A");
            list.addLast("B");
            list.addLast("C");

            System.out.println("First: " + list.getFirst());
            System.out.println("Last: " + list.getLast());

            SequencedCollection<String> reversed = list.reversed();
            System.out.println("Reversed view: " + reversed);
        }


- example of Cache with SequenceCollection


    .. code-block:: python
        :linenos:

        // === Example 1: Stack-like behavior using reversed() ===
        public static void stackLikeBehaviorWithReversedList() {
            System.out.println("\n=== Stack-like behavior with reversed() ===");

            // Create a fixed list of actions (simulating a command history)
            SequencedCollection<String> history = List.of("Open", "Edit", "Save");

            System.out.println("Normal order (oldest to newest): " + history);

            // reversed() gives a view from newest to oldest — similar to popping a stack
            SequencedCollection<String> reversed = history.reversed();
            System.out.println("Reversed (stack-like): " + reversed);

            // We can imagine calling 'undo' on the most recent actions this way
            System.out.println("Undoing last action: " + reversed.getFirst()); // would be "Save"
        }


:ref:`Go Back <java-development-data-structures-label>`.
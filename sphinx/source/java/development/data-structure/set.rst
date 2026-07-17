.. _java-development-data-structure-set:

Set Data Structures
====================
- A set cares about uniqueness.
- It doesn’t allow duplicates.
- It is using equals() method to determine if two objects are equals.
- doesn guarranty any order of the elements or access by the index
- it does not its own methods
- The object inserted must overwrite the hashCode method. If not Object.hashCode() method is used


    .. image:: ../../../images/java/development/data-structures/set-hierarchy.png
        :align: center


- HashSet:
    - is unsorted and unordered  Set
    - based upon HashMap
    - it used hashcode of the object being inserted
    - use this class when you want a collections with no duplicated and don’t care about the order
- LinkedHashSet:
    - based on LinkdHashMap
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
- CopyOnWriteArraySet:
    - is a thread-safe implementation
    - is based on CopyOnWriteArrayList
    - keeps only unique elements
- ConcurentSkipListSet
    - is a thread-safe implementation
    - implements NavigableSet
    - is based on ConcurrentSkipListMap
    - elements are sorted based on natural order or based on a Comparator


    .. image:: ../../../images/java/development/data-structures/hashset-vs-treeset.png
        :align: center

HashSet
-------
- is based on a HashMap
- it uses the same instance of the Object to insert in the internal map
- you can tune the performance of the internal Map by overriding the initialCapacity, loadFactor
- methods:
    - add():
        - adds element to the set
        - returns true in case element is added or false if a preivous element was already existing


    .. code-block:: python
        :linenos:


        public class SetDemo {

            public static void main(String[] args) {
                Set<Integer> set = new HashSet<>();
                System.out.println("Add 1: " + set.add(1)); // => true
                System.out.println("Add 1: " + set.add(1)); // => false
                System.out.println("Add 2: " + set.add(2)); // => true
                System.out.println("Add 3: " + set.add(3)); // => true
                System.out.println(set);                    // => [1, 2, 3]
                
                System.out.println("============ DEMO - objects without hashCode and equals overriden are added to the Set");
                Set<User> users = new HashSet<>();
                User user1 = 
                        new DefaultUser(1, "John", "Smith", "password", "john@email.com");
                
                User user2 = 
                        new DefaultUser(1, "John", "Smith", "password", "john@email.com");
                
                users.add(user1);
                users.add(user2);
                for (User user : users) {
                    System.out.println(user);
                }
                
                System.out.println("============ DEMO - objects with hashCode and equals overriden aren't added to the Set");
                User user3 = 
                        new UserForHashTables(2, "William", "Smith", 
                                "password", "john@email.com");
                
                User user4 = 
                        new UserForHashTables(2, "William", "Smith", 
                                "password", "john@email.com");
                
                users.add(user3);
                users.add(user4);
                for (User user : users) {
                    System.out.println(user);
                }
                
            }
        }


SortedSet 
---------
- a Collection with distinct elements with an order
- has a reference to the first and last element
- methods:
    - comparator()
    - subSet(E fromElement, E toElement)
        - returned value is a subset views, modifying it will modify the original SortedSet
        - fromElement is inclusive, toElement is exclusiv
    - tailSet(E fromElement)
    - headSet(E toElement)
    - first()
    - last()
    - spliterator()


NavigableSet:
-------------
- extends SortedSet
- provides ways to move through the order
- methods:
    - lower()
    - floor()
    - ceiling()
    - pollFirst()
    - pollLast()
    - iterator()
    - descendingSet
    - descendingItertor
    - subSet()
    - headSet()
    - tailSet()
 

Java 9 improvements
-------------------

- above are examples how to create a new Immutable Set:


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

Java 10 improvements
--------------------
- copyOf() was introuced to create a unmodifiable copy of a Set


    .. code-block:: python
        :linenos:
        
        @Test(expected = UnsupportedOperationException.class)
        public void whenModifyCopyOfSet_thenThrowsException() {
            Set<Integer> copySet = Set.copyOf(someIntSet);
            copySet.add(4);
        }

Java 21 improvements
--------------------
- was introduced interface SequenceSet
- is exteding SequenceCollection<E> and Set<E>
- is an ordered set with uniqueness preserved
- existing implementation: LinkedHashSet, TreeSet

    .. code-block:: python
        :linenos:
        
        // === Working with SequencedSet ===
        public static void demoWithSetReversal() {
            System.out.println("\n--- SequencedSet example ---");

            SequencedSet<String> set = new LinkedHashSet<>();
            set.addFirst("first");
            set.addLast("middle");
            set.addLast("last");

            System.out.println("Set in order: " + set);
            System.out.println("First: " + set.getFirst());
            System.out.println("Last: " + set.getLast());
            System.out.println("Reversed: " + set.reversed());
        }

:ref:`Go Back <java-development-data-structures-label>`.
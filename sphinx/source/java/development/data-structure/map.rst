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


    .. image:: ../../../images/java/development/data-structures/map-hierarchy.png
        :align: center



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
    - Dictionary:
        - was created in Java 1 and considered obsulute
    - Hashtable:
        - is an implementation of Dictionary
        - is considered threadsafe version of Map unless ConcurrentHashMAp was introduced in Java 5
        - all methods are synchronized
        - null keys and null values are forbidden
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


Hashtable
-------
- consist of buckets containing ojects with the same hashcode
- buckets are represented as linked lists
- load factor is a value that tells when our Hashtable has to be resized and increased
- during resizing:
    - the threashold will be doubled
    - Hashtable is rehashed so the hashtable has approximatelly twice the nr of buckets
- initial values:
    - initial capacity = 16 buckets
    - load factor = 0.75:
    - threashold = 16 * 0.75 = 12
- when new element is added, it will ba computed the hashcode of the element to determine the index of the bucket to use
- during hash colision (two elements with the same hashcode), the second element will be put in the same bucket (added to the linked list)
- with Java 8, in case key type implements Comparator, meaning it can be sorted, the bucket uses implementation of a binary tree (which will bring performance of O(log(n)) instead of O(n) for searching)
- to retrieve elements, the hashcode of the key is used to retrieve the bucket and to get the value from that backend using equals() method
- in case hashcode is implemented wrongly (ex: returning always value 1), then all values will be placed in the same bucket leading to not have performance of O(1) for get() & put()

LinkdHashMap
------------
- is implemented on the base of HashMap
- it uses a double link list inside to achieve the order of the elements
- when adding a new entry, each entry knows about the previous and the next entry
- the constructor has a new parameter : accessOrder:
    - true for access-order:
        - is ordering by accessing the values, from the least recently accessed to most recently accessed
        - is good when implementing cache
    - false for insertion-order:
        - is the default ordering
        - is order by insertion
- to have a fixed size, you can use method removeEldestEntry():
    - this method is invoked by put() nad putAll() after inserting new entries in map
    - in case this method returns true, it means map should remove its oldest entry


    .. code-block:: python
        :linenos:

        public class LinkedHashMapDemo extends LinkedHashMap<Integer, String> {

            private int capacity = 3;
            
            @Override
            public boolean removeEldestEntry(Entry<Integer, String> eldest) {
                if (size() > this.capacity) {
                    return true;
                }
                return false;
            }

            public static void main(String[] args) {
                LinkedHashMapDemo map = new LinkedHashMapDemo();
                
                map.put(1, "one");
                map.put(2, "two");
                map.put(3, "three");
                map.put(4, "four");
                
                System.out.println(map); // => will print {2=two, 3=three, 4=four}
            }
        }


SortedMap
---------
- is interface
- it extends Map<K, V>
- methods:
    - comparator():
        - return the comparator that is used in map to sort keys or null of such comparator is absent
    - subMap(K fromKey, K toKey):
        - is similar to sublist from list interface
        - it use fromKey inclusively and toKey exclusively
        - can thrown ClassCastException in case keys can't be cast to comparable interface or in case there is no comparator available in this map
        - can throw NulPointerException in case fromKey or toKey are null
        - can throw IllegalArgumentException in case fromKey is higher than toKey
    - headMap(K toKey):
        - returns the view of the map that is less than key given
    - tailMAp(K fromKey):
        - return the view of the map that is higher than key given
    - firstKey()
    - lastKey()
    - keySet():
        - return all keys in ascending order
    - values():
        - return all values in ascending order or the keys
    - entrySet():
        - return the Set of all entries in the ascending order of the keys


NavigableMap
------------
- extends SortedMap
- the main feature of this map is that it returns the closest matches for a given search
- methods:
    - lowerEntry(K key)-> Map.Entry<K, V>:
        - returns an entry that is key value mapping assoctioanted with the greatest key that is striclty less then provided key
    - lowerKey(K key) -> K :
        - return the key which is the greatest key that is strictly less than the given key
    - floorEntry(K key) -> Map.Entry:
        - return the entry associated with the greatest key that is less or equal to the given key
    - floorKey(K key) -> K:
        - return tthe key which is less or equal to the given key
    - ceilingEntry(K key) -> Map.Entry:
        - return the entry associated with the lowest key greated than or equal to the given key
    - ceilingKey(K key) -> K:
        - return the key which is the lowest key greated than or equal to the given key
    - higherEntry(K key) -> Map.Entry:
        - return the entry with the least key that is strictly greater than the given key
    - higherKey(K key) -> K:
        - return the key that is strictly greater than the given key
    - firstEntry() -> Map.Entry
    - lastEntry() -> Map.Entry
    - pollFirstEntry() -> Map.Entry
        - removes and returns the first entry or null in case of null
    - pollLastEntry() -> Map.Entry
        - removes and returns the last entry or null in case of empty
    - descendingMap() -> NavigableMap
        - return the MavigbleMap in revers order
    - navigbleKeySet()
        - returns the NavigableSet<K>
    - descendingKeySet()
    - subMap(K fromKEy, boolean fromInclusive, K toKey, boolean toInclusive):
        - you can specify if you want to include fromKey and toKey to the subMap
    - headMap(K toKey, boolean inclusive):
        - you cam specify if toKey to be included in the submap of all entries with key lower than toKey
    - tailMap (K fromKey, boolean inclusive):
        - you can specify if toKey is included  in the submap of all entries with key giher than fromKey


TreeMap
-------
- extends AbstractMap and implement NavigableMap
- is the most used implementation of NavigableMap
- will throw ClasCastException in case you would put objects that don't implement Comparable interface and no Comparator was provided in the constructor

    .. code-block:: python
        :linenos:


        public class TreeMapDemo {

            public static void main(String[] args) {
                NavigableMap<Integer, String> treeMap = new TreeMap<>();
                treeMap.put(1, "one");
                treeMap.put(5, "five");
                treeMap.put(2, "two");
                treeMap.put(4, "four");
                treeMap.put(3, "three");
                
                System.out.println("Get first entry: " + treeMap.firstEntry());         // => will print entry for 1=one
                System.out.println("Lower entry for 3: " + treeMap.lowerEntry(3));      // => will print entry for 2=two
                System.out.println("Floor entry for 3: " + treeMap.floorEntry(3));      // => will print entry for 3=three
                System.out.println("Higher entry for 3: " + treeMap.higherEntry(3));    // => will print entry for 4=four
                System.out.println("Ceiling entry for 3: " + treeMap.ceilingEntry(3));  // => will print entry for 3=three
                
                System.out.println("Sorted treeMap: " + treeMap); // => {1=one, 2=two, 3=three, 4=four, 5=five}
                System.out.println("Descending order: " + treeMap.descendingMap()); // => {5=five, 4=four, 3=three, 2=two, 1=one}
                
                NavigableMap<Product, User> productUserMap = 
                        new TreeMap<>(new CustomProductComparator());
                productUserMap.put(new DefaultProduct(2, "Oregon Cottage Interior Oak Door", "Doors", 109.98), new DefaultUser());
                productUserMap.put(new DefaultProduct(1, "Hardwood Oak Suffolk Internal Door", "Doors", 109.98), new DefaultUser());
                
                System.out.println("***** Demo - Keys are sorted according to Comparator: ");
                for (Product product : productUserMap.keySet()) {
                    System.out.println(product);
                }
            }
        }


- is based on Red-Black binary tree:
    - it has O(log(n)) for get(), put(), remove(), containsKey()
    - binary tree:
        -  contains nodes or leafs where all children from left are less than current node and all children from right are higher than current node
        - the root is recomputed on each insertion or removal to have almost the same number of nodes on the left comparing to the ones on the right


Requirements for hashCode()
---------------------------
- multiple invcation of hashCode method must consistly return the same integer
- if 2 objects are equal, then their hash codes also should be equal:
    - fields that are used to compare objects and fields that are used to compute the hashCode should be the same
- in case 2 objects are not equal, it is possible that hash codes will be the same

Requirements for equals()
-------------------------
- equals methods should be reflexive:
    - x.equals(x) == true
- equals method should be symetric:
    x.equals(y) == y.equals(X)
- equals mthod should be transitive:
    - x.equals(y) == y.(equals(z) == x.equals(z)
- equals ethod should e consistent
- if x != null, then x.equals(null) == false


Java 9 improvements
-------------------
    - above are examples how to create a new Immutable Map and Map.Entry:

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

Java 10 improvements
--------------------
    - copyOf() was introuced to create a unmodifiable copy of a Map


    .. code-block:: python
        :linenos:
        
        @Test(expected = UnsupportedOperationException.class)
        public void whenModifyCopyOfMap_thenThrowsException() {
            Map<Integer, Integer> copyMap = Map.copyOf(someIntMap);
            copyMap.put(4,4);
        }

Java 21 improvements
--------------------
- was introduced interface SequenceMap
- methods:
    - firstEntry()
    - lastEntry()
    - reverse()
- existing implementation for SequenceMap: LinkedHashMap

    .. code-block:: python
        :linenos:
        
        // === BEFORE JEP 431: Getting first/last from LinkedHashMap manually ===
        public static void demoWithLinkedHashMapBeforeJEP431() {
            System.out.println("\n--- LinkedHashMap BEFORE JEP 431 ---");

            Map<Integer, String> map = new LinkedHashMap<>();
            map.put(1, "one");
            map.put(2, "two");
            map.put(3, "three");

            // Get first entry manually
            Map.Entry<Integer, String> firstEntry = map.entrySet().iterator().next();
            System.out.println("First entry: " + firstEntry);

            // Get last entry manually — not trivial!
            Map.Entry<Integer, String> lastEntry = null;
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                lastEntry = entry;
            }
            System.out.println("Last entry: " + lastEntry);
        }

        // === AFTER JEP 431: cleaner with SequencedMap ===
        public static void demoWithSequencedMapAfterJEP431() {
            System.out.println("\n--- LinkedHashMap AFTER JEP 431 (SequencedMap) ---");

            SequencedMap<Integer, String> map = new LinkedHashMap<>();
            map.putFirst(1, "one");
            map.putLast(2, "two");
            map.putLast(3, "three");

            System.out.println("First entry: " + map.firstEntry());
            System.out.println("Last entry: " + map.lastEntry());

            SequencedMap<Integer, String> reversed = map.reversed();
            System.out.println("Reversed map (view): " + reversed);
        }

- exampe of LRU cache using SequenceMap:
    - cache manages its owne eviction by overiding remove list entry


    .. code-block:: python
        :linenos:

        // === Example 2: LRU cache using SequencedMap ===
        public static void simpleLRUCacheExample() {
            System.out.println("\n=== Simple LRU Cache with SequencedMap ===");

            final int MAX_ENTRIES = 3;

            // LinkedHashMap in access-order mode, now seen as a SequencedMap
            SequencedMap<Integer, String> cache = new LinkedHashMap<>(16, 0.75f, true) {
                protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                    return size() > MAX_ENTRIES;
                }
            };

            // Simulate access to data
            cache.put(1, "A"); // Cache: 1
            cache.put(2, "B"); // Cache: 1, 2
            cache.put(3, "C"); // Cache: 1, 2, 3
            cache.get(1);      // Access 1 → now most recently used → order: 2, 3, 1
            cache.put(4, "D"); // Triggers eviction of least recently used (2) → Cache: 3, 1, 4

            System.out.println("Current cache state (LRU order): " + cache);

            // Demonstrate access to edges
            System.out.println("Oldest entry: " + cache.firstEntry()); // Should be entry 3
            System.out.println("Most recent entry: " + cache.lastEntry()); // Should be entry 4
        }


:ref:`Go Back <java-development-data-structures-label>`.
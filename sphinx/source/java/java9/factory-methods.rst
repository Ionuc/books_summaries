.. _java9-factory-methods:

Factory Methods for Immutable List,Set,Map
==========================================

    - new methods were introduced to create immutable List,Set,Map and Map.Entry objects
    - these methods are used to create empty or non-empty collection objects

Immutable Lists
---------------

    - above are examples how to create a new List:

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

Immutable Sets
--------------

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

Immutable Map and Map.Entry
---------------------------

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
    jshell> Map<Integer,String> emptyImmutableMap = Map.ofEntries(entry(1,"one"),
        ...> entry(2,"two"), entry(3,"three"))
    emptyImmutableMap ==> {1=one, 2=two, 3=three}

    - above are examples of how to create a new Map.Entry:
        - the new methods needs the key and the corresponding value

    .. code-block:: python
        :linenos:

        Map.Entry<Integer,String> immutableMapEntry1 = Map.entry(1,"one")

:ref:`Go Back <java9-label>`.
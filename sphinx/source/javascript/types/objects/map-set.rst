.. _javascript-type-objects-map-set-label:

Map, Set, WeakMap, WeakSet
==========================

Map
---
    - is a collection of keyed data itmes, just like an Object
    - the difference is that Map allows keys of any type, the keys are not converted to String or Symbol
    - main methods are:
        - new Map()
        - map.set(key, value)
        - map.get(key) -> returns the value by the key, or undefined if key doesn't exist in map
        - map.has(key) -> returns true if key exists, false otherwise
        - map.delete(key)
        - map.clean()
        - map.size

    - maps can also have object as keys

    .. code-block:: python
        :linenos:

        let john = { name: "John" };

        // for every user, let's store their visits count
        let visitsCountMap = new Map();

        // john is the key for the map
        visitsCountMap.set(john, 123);

        alert( visitsCountMap.get(john) ); // 123

    - to test values for equivalence, Map uses the algorithm SameValueZero:
        - it is same as strict equality "===" but the difference is that NaN is considered equal to NaN
        - the algorithm can't be changed or customized

    - when we create a Map, it can be passed an array

        .. code-block:: python
            :linenos:

            // array of [key, value] pairs
            let map = new Map([
                ['1',  'str1'],
                [1,    'num1'],
                [true, 'bool1']
            ]);

        - with the convention above, the Map it can be created from Object like:

        .. code-block:: python
            :linenos:

            let map = new Map(Object.entries({
                name: "John",
                age: 30
            }));

    - iteration over map:
        - for looping over a Map, there are 3 methods:
            - map.keys()
            - map.values()
            - map.entries()
            - map.forEach((value, key, map) => {})
        - the iteration goes in the same order as the values were inserted
        - Map preservers the order, unlinke a regular Object

Set
---
    - is a collection of values, where each value may occur only once
    - its main methods are:
        - new Set(iterable)
        - set.add(value) -> add element, returns the set
        - set.delete(value) -> removed the element, returns true if the element was existing on the set
        - set.has(value)
        - set.clear()
        - set.size

    - iteration over Set:
        - we can loop either with 'for..of' or using forEach():
            - set.forEach((value, valueAgain, set) => {})
                - this methods has 3 arguments to be compatible with the Map
        - there are also:
            - set.keys() -> returns an iterable for object for values
            - set.values() -> same as set.keys(), for compatibility with Map
            - set.entries() -> returns an iterable object for entries [value,value], exists for compatibility with Map

WeakMap and WeakSet
-------------------
    - WeakSet is a special kind of Set that does not prevent JavaScript from removing its items from memory
    - WeakMap is the same thing for Map
    - usually, properties of an object or elements of an array of another dat structure are considered reachable and
      kep in memory while that data structure is in memory:

        - for instance, if we put an object in array, the while the array is alive, object will be alive as well, even
          if there are no other references to it

        .. code-block:: python
            :linenos:

            let john = { name: "John" };

            let array = [ john ];

            john = null; // overwrite the reference

            // john is stored inside the array, so it won't be garbage-collected
            // we can get it as array[0]

    - WeakMap and WeakSet do not prevent garbage-collection of key objects
    - WeakMap's keys must be objects, not primitive

    .. code-block:: python
        :linenos:

        let weakMap = new WeakMap();
        let obj = {};
        weakMap.set(obj, "ok"); // works fine (object key)
        // can't use a string as the key
        weakMap.set("test", "Whoops"); // Error, because "test" is not an object

    - if we use an object as the key in it, and there are no other refernced to that object, it will be removed from memory
      and from the map automatically

    .. code-block:: python
        :linenos:

        let john = { name: "John" };

        let weakMap = new WeakMap();
        weakMap.set(john, "...");

        john = null; // overwrite the reference

        // john is removed from memory!

    - WeakMap does not support iteration and methods keys(), values(), entries(), so you cannot get all values are keys
    - WeakMap has only methods:
        - weakMap.get(key)
        - weakMap.set(key, value)
        - weakMap.delete(key)
        - weakMap.has(key)
    - the cleanup is not specified when is happening: it can happen immediately or to wai and do the cleaning later when
      more deletions happen

    - WeakMap is used when we want to streo something for an object that should exist only while the object exists

    .. code-block:: python
        :linenos:

        weakMap.set(john, "secret documents");
        // if john dies, secret documents will be destroyed automatically

    - WeakSet it acts the same as WeakMap:
        - only object can be used in WeakSet
        - an object is kept while is reachable from somewhere else
        - it support add, has, delete, but not size, keys()

        .. code-block:: python
            :linenos:

            let messages = [
                {text: "Hello", from: "John"},
                {text: "How goes?", from: "John"},
                {text: "See you soon", from: "Alice"}
            ];

            // fill it with array elements (3 items)
            let unreadSet = new WeakSet(messages);

            // use unreadSet to see whether a message is unread
            alert(unreadSet.has(messages[1])); // true

            // remove it from the set after reading
            unreadSet.delete(messages[1]); // true

            // and when we shift our messages history, the set is cleaned up automatically
            messages.shift();

            // no need to clean unreadSet, it now has 2 items
            // (though technically we don't know for sure when the JS engine clears it)


:ref:`Go Back <javascript-types-objects-label>`.
.. _types-string-collection-label:

Strings and Collections
=======================

String
-------
    - Strings in python have the data type "str"
    - Strings are sequences of Unicode codepoints
    - you can think of codepoints as being like characters, althhough they aren't strictly equivalent
    - the sequence of Unicode codepoints is immutable
    - literal strings in Python are delimited by quotes. You can use single quotes or double quotes
    - Strings with newlines:
        - use multilen strings:
            - are delimited by 3 quote characters rather than one
        - use escape sequences
    - Python translate the simple '\n' into the universal newlines depending on the platform
    - you can use str() method to return the string representation
    - strings in Python are what are called sequence types, which means they support certain common operations for querying sequences. For example we can access individual characters
      using the index: s[i]

    - operations:
        - concatenation and augment assignment
            - you can use concatenation, but is prefered the join method because it's substantially more efficient:
                - concatenation using the addition operator or its augmented assignment version can lead to the generation of large number of temporaries which brings
                  extra memory allocation and copies

            .. code-block:: python
               :linenos:

               s1 = "New" + "found" + "Land
               s2 = "New"
               s2 += "found"

        - join and split:
            - call the join() method on teh separator string
            - use split to divide a string into a list
            - withou an argument, split() divides on whitespace
            - join()-ing on an empty separator is an important and fast way of concatenating a collection of strings

            .. code-block:: python
               
                colors = ";".join(['a', 'b', 'c']) # will print 'a;b;c'
                colors.split(';')
                "".join["New", "found", "Land"] # will print NewfoundLand

        - partition:
            - the partition() method divides a string into 3 sections around a separator: prefix, separator, suffix, returning the tuple
            - is usefull in combination with the tuple unpacking
            - you can use "_" for unused variariables

            .. code-block:: python
               :linenos:

               "unforgetable".partition("forget") # will print ('un', 'forget', 'able')
               departure, separator, arrival = "London:Endinburgh".partition(':')
               origin, _, destination = "Seattle-Boston".partition('-')

        - format:
            - use format() to insert values into strings
            - replacement fields delimited by "{" and "}"
            - integer field names matched with positional arguments
            - field names can be omitted if used in sequence
            - named fields are matched with keyword arguments

            .. code-block:: python
               :linenos:

               "The age of {0} is {1}".format('Jim', 12)

Bytes
-----
    - are similar to strings, except that rather than being sequences of Unicode codepoints, they are sequences of bytes

        .. code-block:: python
           :linenos:

            b'data'
            b"data"

    - to convert between bytes and strings, we must know the encoding of the bytes sequence used to represent the string's Unicode codepoints as bytes
    - files, network resources such as HTTP responses are transmitted as byte streams

List
----
    - are mutable sequences of objects
    - provides the ability to index from the end rather than from the beginning:
        - this is achived by supplying netavice indexes
        - the last element is at index -1
    - slicing is used to create sublist from a given list by using shallow copies and not deep copies
    - index(item) returns the integer index of the first equivalent element and raises ValueError if not found
    - concatenation list with "+" operator will result in a new list

    .. code-block:: python
       :linenos:

       a = ["apple", "orange", "pear"]
       a[1] = 7
       print(a) => ['apple', 7, 'pear']
       print(a[-3]) => 'apple'

Dictionaries
------------
    - unordered mapping from unique, immutable keys to mutable values

    .. code-block:: python
       :linenos:

       d = ['alice' : '1234, 'bob': '2345', 'eve': '3456']
       print(d['alice']) => '1234'
       for key, value in a.items():
           print("{key} => {value}".format(key=key, value=value))
       for key in a:
           print("{key} => {value}".format(key=key, value=a[key]))
       for value in a.values():
           print(value)

Tuple
-----
    - are immutable sequences of arbitrary objects
    - once created, the objects within them cannot be replaced or removed and new elements cannot be added
    - you can have nested tuples
    - for a single element tuple include a trailing comma

    .. code-block:: python
       :linenos:

       t = ("Norway", 4.953, 3)
       a = ((220,284), (1184,1210))
       h = (391,)

    - delimiting parenthesese are optional for one or more elements
    - tuples are useful for multiple return values
    - tuple unpacking is a destructuring operation which allows us to unpack data structured into named references
    .. code-block:: python
       :linenos:

       def minmax(items):
          return min(items), max(items)
       lower, upper = minmax([]83, 33, 84, 32, 85, 31, 86)

    - you can use the tuple() constructor to create tuples from other data structured, like list, str
    - you can use "in" or "not in" operator

Range
-----
    - is a type fo sequence used for representing an arithmetic progression of integers
    - constructor provides : start, stop, step

    .. code-block:: python
       :linenos:

       range(5) # => 0, 1, 2, 3, 4, 
       range(5,10) # => 5, 6, 7, 8, 9
       range(5,10, 2) # => 5, 7, 9

    - example of abusing range

    .. code-block:: python
       :linenos:

       s = [0, 1, 4, 6]
       for i in range(len(s)):
         print(i)

Set
---
    - is an unordered collection of unique elementes
    - the collection is mutable in so far as elements can be added and removed from the set, but each element must itself be immutable
    - you can use "in" and "not in" operator
    - there are set algebric methods already implemented as : union, intersection, difference, symmetric_difference

    .. code-block:: python
       :linenos:

       p = {6, 26, 496}

Collection Protocols
--------------------
    - Container:
        - requires that membership testing using "in" and "not in" operator be supported
        - collections: str, list, range, tuple, bytes, set, dict
    - Sized:
        - requires that the number of elements in a collection can be determined by passing the collection to the build-in "len" function
        - collections: str, list, range, tuple, bytes, set, dict
    - Iterable:
        - provides a means of yielding elements one-by-one as they-are requested
        - cna produce an iterator with "iter(s)"
        - it can be used with for loops
        - collections: str, list, range, tuple, bytes, set, dict
    - Sequence:
        - requires that items can be retrieved using swuare brackets with an integer index
        - retrieve elements by index : item - seq[index]
        - find items by value: index = seq.idenx(item)
        - count items: num = seq.count(item)
        - produce a reversed sequence: r = reversed(seq)
        - collections: str, list, range, tuple, bytes
    - Mutable Sequence:
        - collections: list
    - Mutable Set:
        - collections: set
    - Mutable Mapping:
        - collections: dict


Filtering Predicates
--------------------
    - general form : [ expr(item) for item in iterable if predicate(item) ]

Iteration protocols
-------------------
    - iterable protocol:
        - Iterable objects can be passed to the built-in iter() function to get an iterator
        - ex: iterator = iter(iterable)
        - method __iter__(self) must be added
    - iterator protocol
        - Iterator objects can be passed to the built-in next() function to fetch the next item
        - ex: item = next(iterator)
        - method __next__(self) must be added
    - alternative to iterable protocol:
        - to provide __getitem__() method


Generators in Python
--------------------
    - specify iterable sequences
    - are lazily avaluated, the next value in the sequence is computed on demand
    - can model infinite sequences, such as data streams with no definit end
    - are composable into pipelines, for natural stream processing
    - are defined by any Python function which used the "yield" keyword at least once in its definition

    .. code-block:: python
       :linenos:

       def gen123():
          yield 1
          yield 2
          yield 3

    - they may also contain the return keyword with no arguments
    - as any other function, there is an implicit return at the end of definition
    - generators are in fact Python iterators
    - generators can maintain state in local variables

    .. code-block:: python
       :linenos:

       def take(count, iterable):
          counter = 0
          for item in iterable:
             if counter == count:
                 return
             counter += 1
             yield item

Comprehensions
---------------
    - comprehensions in Python are concise syntax for describing lists, set or dictionaries in declarative or functional style
    - for list:
        - general form : [ expr(item) for item in iterable ]
    - for set:
        - general form : { expr(item) for item in iterable }
    - for dictionar:
        - general form : { key_expr: value_expr for item in iterable }
    - for generators:
        - general form : ( expr(item) fpr item in interable )

:ref:`Go Back <python-types-label>`.

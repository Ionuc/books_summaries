.. _java-development-concurrency-immutable-data-structures-label:

Immutable Data Structures
=========================

Immutability
------------
- the class is immutable means once the instance of that class is creatd, the state inside cannot be changed from outside
- immutability is useful in concurrent environment
- in Java it can be implemented using final keyword

Benefits
--------
- thread safety:
    - no need for locks
    - cimplifies concurrent programming
    - reduces race conditions
- predictable nebavior
- security:
    - guards against unintended modifications
- performance optimizations
    - allows efficient sharing of internal data structures


:ref:`Go Back <java-development-concurrency-label>`.
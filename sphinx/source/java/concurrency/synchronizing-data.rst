.. _synchronizing-data:

Synchronizing Data
==================

Data Race
---------
    - when reading unsynchronized shared mutable data results in unexpected or incorret values, we have a data race

Volatile
---------
    - add the volatile keyword to shared variable definitions
    - finals cannot be volatile as they are constants
    - arrays and objects references can be marked volatile:
        - doesn't effect the content
    - has several effects:
        - any thread reading it would see the latest value and not an earlier cached value
        - prevent optimization based on program order
        - synchronizes data between threads
            - by installing memory fences

    - memory fence def : a type of barrier instruction that causes a CPU or compiler to enforce an ordering constranint on memory operations issued
      before and after the barrier instruction

    - drawbacks
        - impact performance
        - doesn't guarantee data consistency

How to share memory safely and avoid data races
-----------------------------------------------
    - using volatile
    - publishing immutable objects

64 Bit Primitives
-----------------
    - long / double not thread safe (unless volatile)
        - may be split in two 32 bit operations ot read/write
        - so may read a value never assigned (only one operation carried out)
    - use instead AtomicLong, Long or Double wrappers ( 64 bit references always safe )

:ref:`Go Back <java-concurrency-label>`.
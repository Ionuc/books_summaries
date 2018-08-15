.. _memory-type:

Java Memory
===========
    - Java memory consists of 2 parts:
        - Heap
        - Stack

Heap
----
    - Java Heap space is used by java runtime to allocate memory to Objects and jRE classes
    - Whenever we create any object, it’s always created in the Heap space
    - Garbage Collection runs in the heap memory to free the memory
    - Any object created in the heap space has global access and can be referenced from anywhere of the application
    - When it it becomes full, OutOfMemoryError is thrown

Stack
-----
    - Java Stack memory is used for execution of a thread.
    - Stack memory is always referenced in LIFO order
    - contains:
        - primitives
        - references
        - block methods
    - Stack memory size is very less compared to Heap memory
    - When it it becomes full, StackOverflowError is thrown

Differences between Java Heap Space and Stack Memory
----------------------------------------------------
    - Heap memory is used by all the parts of the application whereas stack memory is used only by the one thread of execution
    - Whenever an object is created, it is created always in the Heap space and stack memory contains the reference to it. Stack memory only contains local primitive variables and reference variable to objects in the heap space
    - Objects stored in the heap are globally accessible whereas stack memory can’t be accessed by other threads
    - Stack memory is short-lived whereas heap memory lives from the start till the end of application execution


:ref:`Go Back <java-memory-management-label>`.
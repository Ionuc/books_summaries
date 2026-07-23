.. _java-tools-memory-management-memory-type:

Java Memory
===========
    - Java memory consists of 2 parts:
        - Heap
        - Stack

Heap
----
    - Java Heap space is used by java runtime to allocate memory to Objects and jRE classes
    - contains:
        - any object created in the application
        - static variables
        - classes definitions
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

Hardware Memory Architecture
----------------------------
- modern computer often has 2 or more central processing units (CPU)
    - this means wew can physically have calculation done in parallel becasue each CPU can run one thread at any given time
- each CPU contains :
    - a set of registers (memory of CPU)
        - that is why CPU can performe operations on these reigsters much faster than it can performe on variables in the main memory
    - CPU cache memory layer:
        - CPU it access must faster cache memroy layer then Ram memory, but slower then the CPU registers

- RAM:
    - stands for Randam Access Memory
    - it is a form of computer memory that can be read and changed in any order
    - is much biger than the cache memories of CPU's


  .. image:: ../../../images/java/tools/memory-management/hardware-memory-architecture.png
        :align: center


- process of reading data from RAM:
    - CPU will read part of data from RAM into CPU cache
    - it is also possible that CPU will read some part of data into internal registers
    - only after that operate on the data

- process of writing data to RAM in CPU:
    - CPU will flush the result of computation from its internal register to the CPU cache
    - flush the value back to RAM
- eviction of CPU Cache:
    - when memory is needed for something else, the cache memroy is flushed back to RAM


JVM & Hardware Memory Architecture
----------------------------------
- both stack and heap memory are located in RAM memory that is reserved for Java process
- part os thread stacks and heap may sometimes be present in CPU caches and in internal CPU registers
- there are 2 main potential problems when storing objects in different parts of memory:
    - visibility of thread updates:
        - there is no guarantee that other threads will be able to see the update by one thread without using propert synchronization or volatile keyword
        - scenario: 
            - initially, object is stored in main memory
            - one thread is running on CPU updating the state of this object
            - as long as CPU cache has not been flushed back to the main memory, the update version of the shared object is not visible to threads running on other CPUs
            - this may end up that each thread contains its own version of the same variable that sitting in different CPU cache
            - to solve this issue, you may use "volatile" keyword
    - race condition during the reading, checking and writing shared variables:
        - one variable is inside cache of 2 CPUs
        - both threads want to increment the variable
        - tha variable will have lost updates because the second CPU which will flush the result to the RAM will override the previous value
        - solution: synchronize blocks

  .. image:: ../../../images/java/tools/memory-management/jvm-hardware-memory-architecture.png
        :align: center


:ref:`Go Back <java-tools-memory-management-label>`.
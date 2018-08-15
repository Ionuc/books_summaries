.. _gc:

Garbage Collector
=================
    - Garbage collection is a form of automatic memory management
    - The garbage collector, or just collector, attempts to reclaim garbage, or memory occupied by objects that are no longer in use by the program
    - Garbage collection was invented by John McCarthy around 1959 to solve problems in Lisp.
    - It works on Heap, which is divided in 3 parts:
        - Young / Egen Generation - for newly created objects
        - Tenured Generation - for old objects which are survived after minor gc
        - Perm Space - for classes, definition, meta data and string pools.


    - At initialization, a maximum address space is virtually reserved but not allocated to physical memory unless it is needed
    - The young generation consists of eden and 2 survivor spaces
    - Most objects are initially allocated in eden
    - One survivor space is empty at any time and serves as the destination of any live objects in eden and the other survivor space during the next copying collection
    - Objects are copied between survivor spaces in this way until they are old enough to be tenured ( copied to the tenured generation )
    - A third generation closely related to the tenured generation is the permanent generation which holds data needed by the virtual machine to describe objects that
      do not have an equivalent at the Java language level.


    .. image:: ../../images/java/memory-management/gc.png
        :align: center

Collectors
----------
    - The Java HotSpot VM includes 3 different collectors, each with different performance characteristics:
        - The serial collector : - uses a single thread to perform all garbage collection work, which makes it relatively efficient
          since there is no communication overhead between threads. It is best-suited to single processor machines, or on multiprocessors
          for application with small data sets.

        - The parallel collector ( known as throughput collector ) - performs minor collections in parallel, which can significantly garbage
          collection overhead. It is intended for application with medium to large sized data set that are run on multiprocessors

        - The concurrent collector performs most of its work concurrently ( while the application is still running) to keep garbage collection
          pauses short. Is recommended for applications where the response time is more important than overall throughput.

    - Parallel compaction is a feature introduced in Java 5 that allows the parallel collector to perform major collections in parallel.
    - Without parallel compaction, major collections are performed using a single thread

Garbage First Collector
-----------------------
    - The heap is partitioned in a set of equal-sized heap regions, each containing range of virtual memory
    - G1 performs a concurrent global marking phase to determine the liveness of objects throughout the heap
    - After the mark phase, G1 knows which regions are mostly empty
    - It collects in these region first, which usually yields a large amount of free space
    - G1 concentrates its collection and compaction activity on the areas of the heap that are likely to be full of reclaimable objects
    - G1 uses a pause prediction model to meet a user-defined pause time target and selects the number of regions to collect based on the specified pause time target.
    - The regions identified by G1 are garbage using evacuation
    - G1 copies objects from one or more regions of the heap to a single region of the heap, and in the process both compacts and free up memory.
    - This evacuation is performed in parallel on multi-processor

How to determine if a GC can be performed or not
------------------------------------------------
    - one solution would be by reference counting:
        - Every time a reference goes out of scope or is set to null, the reference count is decreased
        - The one drawback is that if objects circularly refer to each other they can have nonzero reference counts while still being garbage.
    - any non-dead object must ultimately be traceable back to a reference that lives either on the stack or in static storage:
        - one solution would be : stop-and-copy:
            - This means that—for reasons that will become apparent—the program is first stopped (this is not a background collection scheme)
            - Then, each live object is copied from one heap to another, leaving behind all the garbage.
            - In addition, as the objects are copied into the new heap, they are packed end-to-end, thus compacting the new heap
            - drowbacks:
                - you have two heaps and you slosh all the memory back and forth between these two separate heaps
                - copying process itself
        - other solution is : mark-and-sweep
            - it starts from the stack and static storage, and tracing through all the references to find live objects
            - each time it finds a live object, that object is marked by setting a flag in it, but the object isn’t collected yet
            - Only when the marking process is finished does the sweep occur
            - During the sweep, the dead objects are released
            - However, no copying happens, so if the collector chooses to compact a fragmented heap, it does so by shuffling objects around

:ref:`Go Back <java-memory-management-label>`.
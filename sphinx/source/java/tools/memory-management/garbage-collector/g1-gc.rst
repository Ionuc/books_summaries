.. _java-tools-memory-management-garbage-collector-gc-g1-collector-label:

Garbage First Collector
=======================
- it can be enabled by addug the -XX:+G1GC flag to your JVM application startup parameters
- The G1 (Garbage-First) garbage collector is a sophisticated generational GC designed to improve heap management and offer predictable pause times
- Unlike traditional collectors that divide memory into large contiguous regions, G1 partitions the Java heap into many fixed-size regions, each of which can belong to the Eden, Survivor, Old, or Humongous spaces
- This region-based design provides greater flexibility in managing memory and enables incremental collection of parts of the heap, especially the old generation
- In practice, only the old generation is collected partially, while the young generation is always considered as a whole


  .. image:: ../../../../images/java/tools/memory-management/garbage-collector/g1-collector.png
        :align: center


- G1’s core strategy is to prioritize regions with the most reclaimable garbage
- After a marking phase, G1 estimates how much garbage is in each region and selects those with the highest return on investment to evacuate, minimizing the number of live objects that need to be copied
- This approach helps the collector maintain predictable pause times by focusing work where it’s most effective
- While G1 includes some concurrent phases (such as marking the old generation), the majority of its operations—including object evacuation—remain stop-the-world
- As a result, G1 is better classified as a throughput-oriented collector with bounded latency, rather than a true low-latency GC
- to enable, add XX:+G1GC to JVM application startup parameters

How is working
--------------
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

Java 9 improvement
------------------
- Since JDK 9, G1 has become the default GC on systems with at least two CPUs and 2 GB of heap memory, offering a balanced option for applications that require reasonable throughput with predictable GC behavior

Java 10 improvement
-------------------
- until JAva 9, the full GC for G1 used a single threaded mark-sweep-compact algorithm.
- This has been changed to the parallel mark-sweep-compact algorithm in Java 10 effectively reducing the stop-the-world time during full GC.

:ref:`Go Back <java-tools-memory-management-garbage-collector-label>`.
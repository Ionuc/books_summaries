.. _java-tools-memory-management-garbage-collector-gc-label:

Garbage Collector
=================
    - Garbage collection is a form of automatic memory management
    - The garbage collector, or just collector, attempts to reclaim garbage, or memory occupied by objects that are no longer in use by the program
    -  At its core, GC reclaims memory by freeing objects that are no longer referenced by an application
    - Garbage collection was invented by John McCarthy around 1959 to solve problems in Lisp.

    - there are 2 strategies used by GC which can impact the performance:
        - Generational GC
        - Memory Compaction

Generetional GC
---------------
- Most modern JVMs use a generational GC strategy
- The heap (where all objects in Java are stored) is split into:
    - young generation =>  where new objects are allocated
    - old genertion => where longer-lived objects are promoted
- by focusing frequent, lightweight collections on the young generation, the JVM reduces pause times and overhead
- the young generation is typically divided into multiple regions to optimize allocation and collection efficiency:
    - eden space => where new objects are initially allocated
    - 2 survivor spaces => help manage objects that live long enough to survive initial collections


    .. image:: ../../../../images/java/tools/memory-management/garbage-collector/generational-strategy-1.png
        :align: center


- how is working:
    - One survivor space is empty at any time and serves as the destination of any live objects in eden and the other survivor space during the next copying collection
    - Objects are copied between survivor spaces in this way until they are old enough to be tenured ( copied to the tenured generation )
    - Some references may exist from the old generation pointing to the young generation (called old-to-young references), and these must be tracked to ensure correctness
    - To handle this, the generational garbage collectors use remembered sets: metadata structures that record which parts of the old generation may contain pointers to young objects
    - A write barrier is used during object updates to maintain these sets efficiently
    - Together, these mechanisms ensure that young generation collections remain both accurate and fast, without requiring a full heap scan


  .. image:: ../../../../images/java/tools/memory-management/garbage-collector/generational-strategy-1.png
        :align: center


Memory Compaction
-----------------
- All OpenJDK GCs implement a compacting strategy, which rearranges live objects in memory to reduce fragmentation
- During a GC cycle, surviving objects are moved closer together, either within the same region or copied to another
- At the end of a GC cycle, this compaction frees up a contiguous block of memory, improving heap utilization
- One of the main advantages of this approach is that it enables bump pointer allocation, a fast allocation technique where the GC increments a pointer by the size of the new object to preserve heap memory

- Bump pointer allocation:
    - uses a pointer to the first available byte in memory that is monotonically increased as we continue to allocate objects
    - While this scheme allows fast allocation it comes with the caveat that the free memory must be kept continuous

- In multi-threaded scenarios, this efficiency is preserved by using Thread-Local Allocation Buffers (TLABs): small memory regions assigned per thread to enable lock-free allocation
- Another benefit of using a compacting GC is improved memory locality, as tightly packed objects are more likely to reside in the CPU cache

Signs to tune the GC
--------------------
- Latency sensitivity:
    - Full GC events that align with p99 latency may cause issues and missed deadlines for latency-sensitive applications
    - To determine if the default settings are causing large spikes, you can look at GC logs or APM tools to surface long or frequent pause times, especially from full GC events.
- Throughput presure:
    - In high-throughput applications with large heaps and high allocation rates (e.g., data processing pipelines), the GC might spend too much time collecting
    - This affects overall performance, as applications with high allocation rates may spend more than 10% of CPU time in GC, leading to slowdowns.
    - One indication that your GC is causing throughput drops is if your application appears to be getting slower even though CPU usage is high, and GC logs show frequent and long collections.


Java Collectors
---------------
- The Java HotSpot VM includes 3 different collectors, each with different performance characteristics:
    - The serial collector : 
        :ref:`The serial collector <java-tools-memory-management-garbage-collector-gc-serial-collector-label>`.

    - The parallel collector ( known as throughput collector )
        :ref:`The parallel collector <java-tools-memory-management-garbage-collector-gc-parallel-collector-label>`.

    - Garbage First Collector
        :ref:`Garbage First collector <java-tools-memory-management-garbage-collector-gc-g1-collector-label>`.

    - Shenandoah GC
        :ref:`Shenandoah GC <java-tools-memory-management-garbage-collector-gc-shenandoah-gc-label>`.

:ref:`Go Back <java-tools-memory-management-garbage-collector-label>`.
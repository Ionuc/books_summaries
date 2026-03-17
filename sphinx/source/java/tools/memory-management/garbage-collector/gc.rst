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

Concepts
--------
- Memory management
    - Physical memory is the RAM that our hardware provides
    - The operating system (OS) allocates virtual memory space for each application.
    - we store virtual memory in physical memory, and the OS is responsible for maintaining the mapping between the two. This mapping usually involves hardware acceleration.

- Multi-Mapping
    - Multi-mapping means that there are specific addresses in the virtual memory, which points to the same address in physical memory
    - Since applications access data through virtual memory, they know nothing about this mechanism (and they don’t need to).
    - Effectively, we map multiple ranges of the virtual memory to the same range in the physical memory


  .. image:: ../../../../images/java/tools/memory-management/garbage-collector/gc-multi-mapping.png
        :align: center


- Relocation
    - Since we use dynamic memory allocation, the memory of an average application becomes fragmented over time.
    - It’s because when we free up an object in the middle of the memory, a gap of free space remains there
    - Over time, these gaps accumulate, and our memory will look like a chessboard made of alternating areas of free and used space.
    - we could try to fill these gaps with new objects. To do this, we should scan the memory for free space that’s big enough to hold our object. Doing this is an expensive operation, especially if we have to do it each time we want to allocate memory.
    - The other strategy is to frequently relocate objects from fragmented memory areas to free areas in a more compact format
    - To be more effective, we split the memory space into blocks. We relocate all objects in a block or none of them. This way, memory allocation will be faster since we know there are whole empty blocks in the memory.

- Garbage Collection
    - When we create a Java application, we don’t have to free the memory we allocated, because garbage collectors do it for us
    - In summary, GC watches which objects can we reach from our application through a chain of references and frees up the ones we can’t reach.
    - A GC needs to track the state of the objects in the heap space to do its work.

GC Phase Properties
-------------------
- GC phases can have different properties:

    - a parallel phase can run on multiple GC threads
    - a serial phase runs on a single thread
    - a stop-the-world phase can’t run concurrently with application code
    - a concurrent phase can run in the background, while our application does its work
    - an incremental phase can terminate before finishing all of its work and continue it later

- all of the above techniques have their strengths and weaknesses:
    - A serial implementation of this phase requires 1% of the overall CPU performance and runs for 1000ms
    - In contrast, a parallel implementation utilizes 30% of CPU and completes its work in 50ms.


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

    - Epsilon GC
        :ref:`Epsilon GC <java-tools-memory-management-garbage-collector-epsilon-collector-label>`.
   

:ref:`Go Back <java-tools-memory-management-garbage-collector-label>`.
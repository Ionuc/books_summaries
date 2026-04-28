.. _java-tools-memory-management-garbage-collector-concurrent-mark-sweep-collector-label:

Concurrent Mark Sweep Collector
===============================
- also known as CMS Garbage Collector
- is a mostly concurrent implementation that shares the threads used for garbage collection with the application
- to enable it, add -XX:+UseConcMarkSweepGC to JVM application startup parameters
- is generational which means that you can expect two types of events to happen – minor and major collections
- most work will be done in parallel to the application threads to prevent the tenured generation to get full
- During normal work, most of the garbage collection is done without stopping application threads
- CMS only stops the threads for a very short period of time at the beginning and the middle of the collection during the major collection:
    - The first is called the initial mark pause:
        - It is used to mark the live objects that are directly reachable from the roots and from any other place in the heap
    - The second pause called remark pause:
        - is done at the end of the concurrent tracing phase
        - It finds objects that were missed during the initial mark pause, mainly because of being updated in the meantime
        -  by default it is single-threaded
- The concurrent tracing phase is done between those two pauses:
    -  During this phase, one or more garbage collector threads may be working to clear the garbage
- Minor collections are done in a very similar way to how the Parallel garbage collector works – all application threads are stopped during GC
- After the whole cycle ends the Concurrent Mark Sweep garbage collector waits until the next cycle while consuming close to no resources

- One of the signals that your CMS garbage collector needs tuning is concurrent mode failures:
    - This indicates that the Concurrent Mark Sweep garbage collector was not able to reclaim all unreachable objects before the old generation filled up or there was simply not enough fragmented space in the heap tenured generation to promote objects

- Similar to the Parallel garbage collector, the Concurrent Mark Sweep garbage collector can throw OutOfMemory


Tunning
-------
- CMSInitiatingOccupancyFraction:
    - It is used to set the percentage of the old generation heap utilization when the CMS should start clearing
    - example:  -XX:CMSInitiatingOccupancyFraction=75
    - this is only an informative value and the garbage collector will still use heuristics and try to determine the best possible value
    - a higher value will result that your application will run longer, when the clearing will take also longer time
    - a lower value will result that CMS tenure generation clearing will happen often, but it may be faster 
- UseCMSInitiatingOccupancyOnly:
    - will force the collector to start the clearing when value from CMSInitiatingOccupancyFraction is reached and not use the heuristics value
    - example: XX:+UseCMSInitiatingOccupancyOnly
- CMSScavengeBeforeRemark:
    -  tell our garbage collector to collect the young generation heap during the remark pause
- ScavengeBeforeFullGC:
    - tell our garbage collector to collect the young generation heap before doing the Full GC
- CMSParallelRemarkEnabled:
    -  force the remark phase to use multiple threads
    -  it is not actually always true that the concurrent version of the remark phase will be faster compared to the single-threaded version
    - example: XX:+CMSParallelRemarkEnabled
- UseGCOverheadLimit:
    - to not thrown OutOfMemory

:ref:`Go Back <java-tools-memory-management-garbage-collector-label>`.
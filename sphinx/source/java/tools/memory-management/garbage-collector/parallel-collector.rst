.. _java-tools-memory-management-garbage-collector-gc-parallel-collector-label:

Parallel Garbage Collector
==========================

 - works similarly to Serial GC in terms of its basic algorithm but parallelizes most of its phases
- By using multiple threads to perform garbage collection, it makes more efficient use of available CPU cores, significantly improving throughput and reducing pause times compared to single-threaded collectors
- The performance of Parallel GC scales with the number of cores. More CPU cores mean more parallel work during GC cycles,
- performs minor collections in parallel, which can significantly garbage collection overhead. It is intended for application with medium to large sized data set that are run on multiprocessors
- The concurrent collector performs most of its work concurrently ( while the application is still running) to keep garbage collection pauses short. Is recommended for applications where the response time is more important than overall throughput.

- goals:
    - achieve maximum pause time
    - achieve throughput, only if pause time is achieved
    - achieve footprint only if the first two goals are achieved
- The Parallel garbage collector grows and shrinks the generations, to achieve the goals above
    - Growing and shrinking the generations is done in increments at a fixed percentage
    - By default, the generation grows in increments of 20% and shrinks in increments of 5%

- If the pause time goal is not achieved the generations will be shrunk one at a time
- If the pause time of both generations is above the goal, the generation that caused threads to stop for a longer period of time will be shrunk first
- If the throughput goal is not met then both the young and old generations will be grown.

- The Parallel garbage collector can throw Java OutOfMemoryError exception if too much time is spent in garbage collection
- By default, if more than 98% of the time is spent in garbage collection and less than 2% of the heap is recovered such an exception will be thrown


Tunning
-------
- UseParallelGC
    - to enable this collector, add "XX:+UseParallelGC" to JVM application startup parameters
- ParallelGCThreads:
    - set number of threads that the garbage collector can use 
    - example:  -XX:ParallelGCThreads=4
    - advantage:
        - the more threads you dedicate to cleaning duties the faster it can get
    - drowbacks:
        - Each GC thread involved in a minor garbage collection event will reserve a portion of the tenured generation heap for promotions
        - This will create divisions of space and fragmentation: The more the threads the higher the fragmentation:
            - Reducing the number of Parallel garbage collection threads and increasing the size of the old generation will help with the fragmentation if that becomes an issue
- MaxGCPauseMillis:
    - it specifies the maximum pause time goal between two consecutive garbage collection events
    - example: XX:MaxGCPauseMillis=100
    - The longer the gap between garbage collections the more garbage can be left on the heap making the next garbage collection more expensive.
    - On the other hand, if the value is too small, the application will spend the majority of its time in garbage collection instead of executing business logic
- GCTimeRatio:
    - used to set maximum throughput target
    - It defines the ratio between the time spent in GC and the time spent outside of GC
    - It is defined as 1/(1 + GC_TIME_RATIO_VALUE) and it’s a percentage of time spent in garbage collection
    - example : -XX:GCTimeRatio=9
        - it means that 10% of the application’s working time may be spent in the garbage collection
    - default value of -XX:GCTimeRatio flag is set to 99 by the JVM,
- YoungGenerationSizeIncrement:
    - The percentage of the growth of a young generation
- TenuredGenerationSizeIncrement:
    - The growth of the old generation
- AdaptiveSizeDecrementScaleFactor:
    - The shrinking part
- UseGCOverheadLimit:
    - to disable the Java OutOfMemoryError thrown
    - example: -XX:-UseGCOverheadLimit

:ref:`Go Back <java-tools-memory-management-garbage-collector-label>`.
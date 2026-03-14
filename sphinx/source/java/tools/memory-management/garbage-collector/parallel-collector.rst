.. _java-tools-memory-management-garbage-collector-gc-parallel-collector-label:

Parallel Garbage Collector
==========================

 - works similarly to Serial GC in terms of its basic algorithm but parallelizes most of its phases
- By using multiple threads to perform garbage collection, it makes more efficient use of available CPU cores, significantly improving throughput and reducing pause times compared to single-threaded collectors
- The performance of Parallel GC scales with the number of cores. More CPU cores mean more parallel work during GC cycles,
- performs minor collections in parallel, which can significantly garbage collection overhead. It is intended for application with medium to large sized data set that are run on multiprocessors
- The concurrent collector performs most of its work concurrently ( while the application is still running) to keep garbage collection pauses short. Is recommended for applications where the response time is more important than overall throughput.


:ref:`Go Back <java-tools-memory-management-garbage-collector-label>`.
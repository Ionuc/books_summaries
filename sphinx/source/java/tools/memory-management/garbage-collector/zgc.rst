.. _java-tools-memory-management-garbage-collector-gc-zgc-gc-label:

Zgc Garbage Collector
============================
- is a low-latency garbage collector developed by Oracle for OpenJDK and designed with goals similar to Shenandoah:
    - minimizing pause times by performing nearly all GC work—including compaction—concurrently with application threads
- Like Shenandoah, it uses read barriers to maintain reference consistency while objects are being relocated in the heap
- As of JDK 21, ZGC introduced a generational mode, which further improves performance by separating short-lived and long-lived objects. This generational version became the default in JDK 23, offering better throughput while maintaining ZGC’s hallmark low-pause behavior.

:ref:`Go Back <java-tools-memory-management-garbage-collector-label>`.
.. _java-tools-memory-management-garbage-collector-gc-shenandoah-gc-label:

Shenandoah Garbage Collector
============================
- is a low-latency garbage collector introduced by Red Hat for OpenJDK and designed to keep pause times consistently under 10 ms
- To achieve this, most GC operations—including object evacuation—run concurrently with application threads
- Because moving objects during runtime is complex, Shenandoah uses read barriers to ensure references remain valid even if an object has been relocated
- These barriers are implemented during reference loads and benefit from JIT compiler optimizations, allowing a single reference check to serve multiple field accesses
- If a thread encounters an object that hasn’t yet been moved, it can complete the evacuation itself and effectively redistribute GC work to application threads for reduced coordination overhead and smooth pause behavior
- While this approach sacrifices some throughput, it provides strong latency guarantees, making Shenandoah a good fit for web servers and user-facing applications where responsiveness is critical.

:ref:`Go Back <java-tools-memory-management-garbage-collector-label>`.
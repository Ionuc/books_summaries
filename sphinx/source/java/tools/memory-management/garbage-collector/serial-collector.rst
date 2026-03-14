.. _java-tools-memory-management-garbage-collector-gc-serial-collector-label:

Serial Garbage Collector
========================

- is designed for environments with limited CPU and memory resources
- It is a stop-the-world collector, meaning that all application threads are paused during garbage collection phases
- As its name implies, it uses a single thread to perform all GC work, which makes it ideal for small heaps and low-core-count systems.
- By default, the JVM selects Serial GC when it detects fewer than two CPUs or a heap size under 2 GB
- Serial GC is still a generational collector, dividing the heap into young and old generations
- It performs minor GCs for the young generation and full GCs for the entire heap, providing a basic but effective memory management strategy for constrained workloads

:ref:`Go Back <java-tools-memory-management-garbage-collector-label>`.
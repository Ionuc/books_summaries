.. _java12_shenandoah:

Shenandoah: A Low-Pause-Time Garbage Collector
==============================================

    - Shenandoah is an experimental garbage collection (GC) algorithm, for now not included in the default Java 12 builds.
    - it reduces the GC pause times by doing evacuation work simultaneously with the running Java threads
    - this means that with Shenandoah, pause times are not dependent on the heap’s size and should be consistent
    - Garbage collecting a 200 GB heap or a 2 GB heap should have a similar low pause behavior.
    - Shenandoah will become part of mainline JDK builds since version 15.


:ref:`Go Back <java12-jvm-label>`.
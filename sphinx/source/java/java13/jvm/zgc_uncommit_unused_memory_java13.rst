.. _java13_zgc_uncommit_unsed_memory:

ZGC: Uncommit Unsed Memory
==========================

    - the Z Garbage Collector was introduced in Java 11 as a low-latency garbage collection mechanism, such that GC pause times never exceeded 10 ms
    - However, unlike other HotSpot VM GCs such as G1 and Shenandoah, it was not equipped to return unused heap memory to the operating system.
    - Java 13 added this capability to the ZGC.
    - Starting with Java 13, the ZGC now returns uncommitted memory to the operating system by default, up until the specified minimum heap size is reached



    .. code-block:: python
           :linenos:

            Using option -XX:-ZUncommit, or
            Setting equal minimum (-Xms) and maximum (-Xmx) heap sizes


    - ZGC now has a maximum supported heap size of 16TB. Earlier, 4TB was the limit


:ref:`Go Back <java13-jvm-label>`.
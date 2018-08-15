.. _jvm:

JVM
===

Code Reordering
---------------
    - Compiler, JVM or processor can reorder code:
        - to make it execute faster
    - it does not notice when single threaded (except maybe when using debugger)
    - can be harmful when multithreaded
    - optimizations are platform specific

:ref:`Go Back <java-jvm-label>`.
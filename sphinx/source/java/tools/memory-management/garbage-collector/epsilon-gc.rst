.. _java-tools-memory-management-garbage-collector-epsilon-collector-label:

Epsilon Garbage Collector
==========================
- introduced with JAva 11

- it’s called a No-Op (no operations) because it allocates memory but does not actually collect any garbage. Thus, Epsilon is applicable for simulating out of memory errors
- use-cases:
    - Performance testing
    - Memory pressure testing
    - VM interface testing and
    - Extremely short-lived jobs



    .. code-block:: python
           :linenos:

            -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC


:ref:`Go Back <java-tools-memory-management-garbage-collector-label>`.
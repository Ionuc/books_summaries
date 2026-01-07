.. _java11_no_op_gc:

A No-Op Garbage Collector
=========================

    - a new garbage collector called Epsilon is available for use in Java 11 as an experimental feature
    - it’s called a No-Op (no operations) because it allocates memory but does not actually collect any garbage. Thus, Epsilon is applicable for simulating out of memory errors
    - use-cases:
        - Performance testing
        - Memory pressure testing
        - VM interface testing and
        - Extremely short-lived jobs



    .. code-block:: python
           :linenos:

            -XX:StartFlightRecording=duration=120s,settings=profile,filename=java-demo-app.jfr



:ref:`Go Back <java11-performance-features-label>`.
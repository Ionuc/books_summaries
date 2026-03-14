.. _java-tools-jvm-jvm-label:

JVM
===

Code Reordering
---------------
    - Compiler, JVM or processor can reorder code:
        - to make it execute faster
    - it does not notice when single threaded (except maybe when using debugger)
    - can be harmful when multithreaded
    - optimizations are platform specific

Java 10 improvement
-------------------
- Container Awareness

    - JVMs are now aware of being run in Docker container and will extract container-specific configuration instead of querying the operation system itself, like number of CPUs, total memory allocated to container, etc
    - However, this support is only available for Linux-based platforms
    - in case you want to disable it, add above JVM options

    .. code-block:: python
           :linenos:

            -XX:-UseContainerSupport

- this change adds a JVM option that provides the ability to specify the number of CPUs that the JVM will use


    .. code-block:: python
           :linenos:

            -XX:ActiveProcessorCount=count


- Also, three new JVM options have been added to allow Docker container users to gain more fine-grained control over the amount of system memory that will be used for the Java Heap:


    .. code-block:: python
           :linenos:

            -XX:InitialRAMPercentage
            -XX:MaxRAMPercentage
            -XX:MinRAMPercentage



:ref:`Go Back <java-tools-jvm-label>`.
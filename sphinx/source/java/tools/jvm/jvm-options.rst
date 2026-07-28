.. _java-tools-jvm-options-label:

JVM options
===========


Java 11 Improvements
--------------------
- Docker Container Support
    - a new command line option was introduced to address container-related issues
    - when running Java apps in Docker container, the JVM can detect container-specific configurations and adapt its behavior

    .. code-block:: python
        :linenos:

        -XX:-USeContainerSupport


- Flexible Heap Size
    - -XX:InitialRAMPercentage
        - specifies the initial heap size as a percentage of the total available system memory
    - --X:MAxRAMPercentage
        - specifies the maxium heap size as a percentage of the total available system memory
    - -XX:MinRAMPercentag
        - specifies the minumum heap size as a percentage of the total available system memory


:ref:`Go Back <java-tools-label>`.
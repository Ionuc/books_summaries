.. _java10_container_awareness:

Container Awareness
===================

    - JVMs are now aware of being run in Docker container and will extract container-specific configuration instead of
      querying the operation system itself, like number of CPUs, total memory allocated to container, etc

    - this is supported only for Linux based paltforms

    .. code-block:: python
           :linenos:

            @Test
            public void whenListContainsInteger_OrElseThrowReturnsInteger() {
                Integer firstEven = someIntList.stream()
                  .filter(i -> i % 2 == 0)
                  .findFirst()
                  .orElseThrow();
                is(firstEven).equals(Integer.valueOf(2));
            }


:ref:`Go Back <java10-label>`.
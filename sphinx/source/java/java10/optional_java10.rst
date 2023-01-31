.. _java10_optional:

Optional.orElseThrow()
======================

    - java.util.Optional, java.util.OptionalDouble, java.util.OptionalInt and java.util.OptionalLong has a new method
      orElseThrow() which is throwing NoSuchElementException if value is not present

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
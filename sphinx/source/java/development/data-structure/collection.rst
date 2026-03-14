.. _java-development-data-structure-collection-label:

Collection Interface
====================

Java 11 improvement
-------------------

- new method was added to Collection interface to make easier to create a collection from a list

    .. code-block:: python
           :linenos:

            List sampleList = Arrays.asList("Java", "Kotlin");
            String[] sampleArray = sampleList.toArray(String[]::new);
            assertThat(sampleArray).containsExactly("Java", "Kotlin");

:ref:`Go Back <java-development-data-structures-label>`.
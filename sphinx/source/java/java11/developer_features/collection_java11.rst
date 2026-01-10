.. _java11_collection:

Collections
============

    - new method was added to Collection interface to make easier to create a collection from a list

    .. code-block:: python
           :linenos:

            List sampleList = Arrays.asList("Java", "Kotlin");
            String[] sampleArray = sampleList.toArray(String[]::new);
            assertThat(sampleArray).containsExactly("Java", "Kotlin");


:ref:`Go Back <java11-developer-features-label>`.
.. _java-development-function-predicate-label:

Predicate
=========


Java 11 improvement
-------------------
- A static not method has been added to the Predicate interface. We can use it to negate an existing predicate, much like the negate method:


    .. code-block:: python
           :linenos:

            List<String> sampleList = Arrays.asList("Java", "\n \n", "Kotlin", " ");
            List withoutBlanks = sampleList.stream()
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.toList());
            assertThat(withoutBlanks).containsExactly("Java", "Kotlin");


:ref:`Go Back <java-development-function-label>`.
.. _java11_not_predicate:

Not Predicate
=============

    - A static not method has been added to the Predicate interface. We can use it to negate an existing predicate, much like the negate method:



    .. code-block:: python
           :linenos:

            List<String> sampleList = Arrays.asList("Java", "\n \n", "Kotlin", " ");
            List withoutBlanks = sampleList.stream()
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.toList());
            assertThat(withoutBlanks).containsExactly("Java", "Kotlin");


:ref:`Go Back <java11-developer-features-label>`.
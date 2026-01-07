.. _java11_string:

String
======

    - new methods were added to String: iBlank(), lines(), strip(), stipLeading(), stipTrailing() and repeat()

    .. code-block:: python
           :linenos:

            String multilineString = "Baeldung helps \n \n developers \n explore Java.";
            List<String> lines = multilineString.lines()
                .filter(line -> !line.isBlank())
                .map(String::strip)
                .collect(Collectors.toList());
            assertThat(lines).containsExactly("Baeldung helps", "developers", "explore Java.");


:ref:`Go Back <java11-developer-features-label>`.
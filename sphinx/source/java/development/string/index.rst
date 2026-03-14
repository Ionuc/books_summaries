.. _java-development-string-label:

String
======

Java 11 improvements
--------------------
- new methods were added to String: iBlank(), lines(), strip(), stipLeading(), stipTrailing() and repeat()

    .. code-block:: python
           :linenos:

            String multilineString = "Baeldung helps \n \n developers \n explore Java.";
            List<String> lines = multilineString.lines()
                .filter(line -> !line.isBlank())
                .map(String::strip)
                .collect(Collectors.toList());
            assertThat(lines).containsExactly("Baeldung helps", "developers", "explore Java.");



.. toctree::
    :maxdepth: 2
    :caption: Contents:

    string-joiner.rst
    format-specifiers.rst
    string-regular-expression.rst

:ref:`Go Back <java-development-label>`.

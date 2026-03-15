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


Java 12 improvements
--------------------
- new methods were added to String: indent() & transform()

- String.indent
    - adjusts the indentation of each line based on the integer parameter.
    - if the parameter is greater than zero, new spaces will be inserted at the beginning of each line
    - if the parameter is less than zero, it removes spaces from the begging of each line. If a given line does not contain sufficient white space, then all leading white space characters are removed

    .. code-block:: python
           :linenos:

            String text = "Hello Baeldung!\nThis is Java 12 article.";

            text = text.indent(4);
            System.out.println(text);

            text = text.indent(-10);
            System.out.println(text);


    - Output:


    .. code-block:: python
           :linenos:

                Hello Baeldung!
                This is Java 12 article.

            Hello Baeldung!
            This is Java 12 article.


- String.transform
    - It accepts a single argument function as a parameter that will be applied to the string.


    .. code-block:: python
           :linenos:

            @Test
            public void givenString_thenRevertValue() {
                String text = "Baeldung";
                String transformed = text.transform(value ->
                    new StringBuilder(value).reverse().toString()
                );

                assertEquals("gnudleaB", transformed);
            }


.. toctree::
    :maxdepth: 2
    :caption: Contents:

    string-joiner.rst
    format-specifiers.rst
    string-regular-expression.rst

:ref:`Go Back <java-development-label>`.

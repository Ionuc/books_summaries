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


Java 13 Improvements
--------------------

    - is used for multi-lines Strings such as embedded JSON, XML, HTML, etc
    - with old version, to embed JSON in code, the String would have been created as 

    .. code-block:: python
           :linenos:

            String JSON_STRING = "{\r\n" + "\"name\" : \"Baeldung\",\r\n" + "\"website\" : \"https://www.%s.com/\"\r\n" + "}";



    - these JSON can be rewriten as 


    .. code-block:: python
           :linenos:

            String TEXT_BLOCK_JSON = """
            {
                "name" : "Baeldung",
                "website" : "https://www.%s.com/"
            }
            """;


    - there is no need to escape double quotes or to add a carriage return 
    - all String functions are available for text blocks


    .. code-block:: python
           :linenos:

            @Test
            public void whenTextBlocks_thenStringOperationsWorkSame() {        
                assertThat(TEXT_BLOCK_JSON.contains("Baeldung")).isTrue();
                assertThat(TEXT_BLOCK_JSON.indexOf("www")).isGreaterThan(0);
                assertThat(TEXT_BLOCK_JSON.length()).isGreaterThan(0);
            }



.. toctree::
    :maxdepth: 2
    :caption: Contents:

    string-joiner.rst
    format-specifiers.rst
    string-regular-expression.rst

:ref:`Go Back <java-development-label>`.

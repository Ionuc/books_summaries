.. _java-development-data-structures-string-text-blocks:

String Text Blocks
==================
    - is introduced with Java 13
    - it was introduced the Text Blocks
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

Java 14 improvement
-------------------
- text blocks now have 2 new escape sequances:
        - \: to indicate the end of the line, so that a new line character is not introduced
        - \s: to indicate a single space

    .. code-block:: python
           :linenos:

            // Old way
            String multiline = "A quick brown fox jumps over a lazy dog; the lazy dog howls loudly.";

            // New way
            String multiline = """
                A quick brown fox jumps over a lazy dog; \
                the lazy dog howls loudly.""";


    - it does not add a new line after dog;.

Java 15 Improvements
--------------------
- now it will be part of production code and no more preview mode

:ref:`Go Back <java-development-data-structures-string-label>`.
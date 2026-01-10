.. _java13_text_blocks:

Text Blocks
===========

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





:ref:`Go Back <java13-preview-label>`.
.. _java11_local_variabe_lambda:

Local Variable Syntax fir Lambda
================================

    - Support for using the local variable syntax (var keyword) in lambda parameters was added in Java 11. 
    - We can make use of this feature to apply modifiers to our local variables, like defining a type annotation



    .. code-block:: python
           :linenos:

            List<String> sampleList = Arrays.asList("Java", "Kotlin");
            String resultString = sampleList.stream()
                .map((@Nonnull var x) -> x.toUpperCase())
                .collect(Collectors.joining(", "));
            assertThat(resultString).isEqualTo("JAVA, KOTLIN");


:ref:`Go Back <java11-label>`.
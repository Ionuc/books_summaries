.. _frameworks-java-spring-mvc-annotation-post-mapping-label:

PostMapping
==========
- annotation used to bind a method controller only to a request with RequestMethod.POST
- has no limitation on data length
- can also send binary data, like file content


    .. code-block:: python
        :linenos:

        // need a controller method to read form data and
        // add data to the model

        @Controller
        public DemoController {
            ...
            @PostMapping("/processForm")
            public String processForm(...) {
                ...
            }
        }


:ref:`Go Back <frameworks-java-spring-mvc-annotation-label>`.

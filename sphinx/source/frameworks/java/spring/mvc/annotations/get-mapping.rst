.. _frameworks-java-spring-mvc-annotation-get-mapping-label:

GetMapping
==========
- annotation used to bind a method controller only to a request with RequestMethod.GET
- limitations on data length


    .. code-block:: python
        :linenos:

        // need a controller method to read form data and
        // add data to the model

        @Controller
        public DemoController {
            ...
            @GetMapping("/processForm")
            public String processForm(...) {
                ...
            }
        }


:ref:`Go Back <frameworks-java-spring-mvc-annotation-label>`.

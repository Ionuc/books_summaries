.. _frameworks-java-spring-mvc-annotation-model-attribute-label:

ModelAttribute
==============
- annotation used to bind a method the method parameter to the actual model
- the parameter will have directly the Java POJO class
- 


    .. code-block:: python
        :linenos:

        // need a controller method to read form data and
        // add data to the model

        @Controller
        public DemoController {
            ...
            @PostMapping("/processStudentForm")
            public String processForm(@ModelAttribute("student") Student student) {
                ... process Student object
            }
        }


:ref:`Go Back <frameworks-java-spring-mvc-annotation-label>`.

.. _frameworks-java-spring-rest-annotations-rest-controller-label:

RequestMapping
==============
- is to add a prefix for all endpoints for the given controller
- in example above, access teh REST endpoint at /api/hello


    .. code-block:: python
        :linenos:

        @RestController
        @RequestMappings("/api")
        public class StudentRestController{
            @GetMapping("/hello")
            public String sayHello() {
                return "Hello World!";
            }
        }
:ref:`Go Back <frameworks-java-spring-rest-annotations-label>`.
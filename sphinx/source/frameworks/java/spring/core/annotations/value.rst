.. _frameworks-java-spring-core-annotation-value-label:

Value
=====
- used to inject into components config values specified in application.properties
- config key should match the same name specified in @Value annotation
- will delimit automatically by comma: ","

    .. code-block:: python
        :linenos:

        // application.properties
        countries=Brazil,France,Germany,India,United States

        @RestController
        public class DemoContronller {

            @Value("${countries}")
            private List<String> countries
            ...
        }


:ref:`Go Back <frameworks-java-spring-core-annotation-label>`.

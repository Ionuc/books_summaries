.. _frameworks-java-spring-core-injection-types-label:

Injection Types
===============
- there are multiple type of injection with Spring
    - constructor injection:
        - use this when you have required dependencies
        - generally recommended by the spring.io development team as a first choise

    .. code-block:: python
        :linenos:

        @RestController
        public class DemoController{
            private Coach myCoach;

            @Autowire
            public DemoController(Coach theCoach) {
                myCoach = theCoach;
            }
        }


    - setter injection
        - use it when you have optional dependencies
        - in case dependency is not provided, the application should provide a default logic
        - method annotated with @Autowire can have any name, not necessary to start with "setXXX"


    .. code-block:: python
        :linenos:

        @RestController
        public class DemoController{
            private Coach myCoach;

            @Autowire
            public void setCoach(Coach theCoach) {
                myCoach = theCoach;
            }
        }


    - field injection:
        - not recommended by spring.io development team
        - it makes the code harder to unit test
        - fields is initialized by using java reflection, even if the fields is private

    .. code-block:: python
        :linenos:

        @RestController
        public class DemoController{
            @Autowire
            private Coach myCoach;

        }


    - java configuration:
        - the classes are not having the @Component annotations
        - is used to make an existing third-pary class availale to Spring framework where you may not have access to the source code
        - steps:
            - 1. create @Configuration class:
                - is the class to define in Spring the custom java configuration


    .. code-block:: python
        :linenos:

        @Configuration
        public class SportConfig{
        }


            - 2. define @Bean method to configure the bean:
                - the bean is manually created
                - the bean id defaults to the method name. It can be overriden inside @Bean annotation


    .. code-block:: python
        :linenos:

        @Configuration
        public class SportConfig{
            @Bean
            public Coach swimCoach() {
                return new SwimCoach();
            }

        }


            - 3. inject the bean into needed class (in our case in the controller)


    .. code-block:: python
        :linenos:

        @RestController
        public class DemoController{
            private Coach myCoach

            @Autowire
            public DemoController(@Qualifier("swimCoach") Coach theCoach) {
                myCoach = theCoach;
            }

        }


:ref:`Go Back <frameworks-java-spring-core-label>`.
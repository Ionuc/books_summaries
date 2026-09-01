.. _frameworks-java-spring-core-annotation-lazy-label:

Lazy
====
- by default, when the application starts, all beans are created and initialized
- instead of always creating it, it can be used lazy-initialization
- with lazy-initializaiton, a bean will be created:
    - only it is needed for dependency injection
    - it is explicitly requested

    .. code-block:: python
        :linenos:

        @RestController
        public class DemoController{
            private Coach myCoach;

            @Autowire
            public void DemoController(Coach theCoach) {
                myCoach = theCoach;
            }

            @GetMapping("/dailyworkout")
            public String getDailyWorkout() {
                return myCoach.getDailyWorkout();
            }
        }

        interface Coach {
            String getDailyWorkout();
        }

        @Component
        @Primary
        class CricketCoach implements Coach {
            @Override
            public String getDailyWorkout() {
                return "Cricket coach daily workout";
            }
        }

        @Component
        @Lazy
        class BaserballCoach implemets Coach {
            @Override
            public String getDailyWorkout() {
                return "Baseball coach daily workout";
            }
        }

Types
-----
- it can be set:
    - for each component using @Lazy annotation
        - usefull for small application
    - global context:
        - using application property:
            - will be applied for all components, even for controllers
            - spring.main.lazy-initialization=true

Adavantages
-----------
- only create objects as needed
- may help with faster startup if you have large number of components

Disadvatages
------------
- it you have a web related components, like @RestController, the controller won't be created until requested
- may not discover configuration issues until too late
- need to make sure you have enough memory for all beans once created

:ref:`Go Back <frameworks-java-spring-core-annotation-label>`.

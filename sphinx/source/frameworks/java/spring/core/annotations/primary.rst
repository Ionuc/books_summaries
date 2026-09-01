.. _frameworks-java-spring-core-annotation-primary-label:

Primary
=======
- is an alternative solution for @Qualifiers
- is set on an implementation of a Component
- component having this implementation will be retrived and injected with @Autowire, in case Qualifiers are not set
- only one implementation can have @Primary annotation, or else the application cannot start
- it can be used together with @Qualifier, but Qualifier has the higher priority and will be used


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
        class BaserballCoach implemets Coach {
            @Override
            public String getDailyWorkout() {
                return "Baseball coach daily workout";
            }
        }


:ref:`Go Back <frameworks-java-spring-core-annotation-label>`.

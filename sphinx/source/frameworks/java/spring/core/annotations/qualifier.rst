.. _frameworks-java-spring-core-annotation-qualifier-label:

Qualifier
=========
- is used to specify which bean should be used in case multiple implementation of the same interface / class is found
- should be use together with @Autowire annotation
- it can be used from both setter & constructor initialization
- in case multiple beans are found and no @Qualifiers are used, then the Spring application will not start
- it is specified an "beanId":
    - can be the implementation class name having first character with lower case

    .. code-block:: python
        :linenos:

        @RestController
        public class DemoController{
            private Coach myCoach;

            @Autowire
            public void DemoController(@Qualifier("cricketCoach") Coach theCoach) {
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

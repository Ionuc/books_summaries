.. _frameworks-java-spring-core-annotations-bean-scope-label:

Bean Scope
==========
- scope refers
    - to the lifecycle of a bean
    - how long does the bean live
    - how many instances are created
    - how is the bean shared
- default scope in Spring is SINGLETON
- it can be set by using @Scope annotation

    .. code-block:: python
        :linenos:

        interface Coach {
            String getDailyWorkout();
        }

        @Component
        @Scope(ConfigurationBeanFactory.SCOPE_SINGLETON)
        class CricketCoach implements Coach {
            @Override
            public String getDailyWorkout() {
                return "Cricket coach daily workout";
            }
        }


Types
-----
- there are multiple types of scope:
    - SINGLETON:
        - creates only one shared instance 

    .. code-block:: python
        :linenos:

        @RestController
        public class DemoController{
            private Coach myCoach;
            private Coach anotherCoach;

            @Autowire
            public void setCoach(@Qualifier("cricketCoach") Coach theCoach, @Qualifier("cricketCoach") Coach theAnotherCoach) {
                myCoach = theCoach;
                anotherCoach = theAnotherCoach; // => is the same referece as myCoach
            }
        }
    

    - PROTOTYPE:
        - creates new bean instance for each container request or for each injection point

    .. code-block:: python
        :linenos:

        @RestController
        public class DemoController{
            private Coach myCoach;
            private Coach anotherCoach;

            @Autowire
            public void setCoach(@Qualifier("cricketCoach") Coach theCoach, @Qualifier("cricketCoach") Coach theAnotherCoach) {
                myCoach = theCoach;
                anotherCoach = theAnotherCoach; // => is a new instance of Coach referring different part of memory
            }
        }


    - REQUEST:
        - scope to an HTTP web request
        - only used for web apps
    - SESSION:
        - scope to an HTTP web session
        - only used for web apps
    - APPLICATION:
        - scope t oa web app ServletContext
        - only used for web apps
    - WEBSOCKET:
        - scope to a web socket
        - only used for web apps

:ref:`Go Back <frameworks-java-spring-core-annotations-label>`.
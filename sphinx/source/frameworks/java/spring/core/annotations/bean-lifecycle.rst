.. _frameworks-java-spring-core-annotations-bean-lifecycle-label:

Bean Lifecycle
==============
- the general bean lifecycle is:
    - container started -> Bean Instantiated -> Dependencies injected -> Internal Spring Processing -> Your custom init method -> Custom Utility method -> Your custom Destroy Method -> STOP



    .. image:: ../../../../../images/frameworks/java/spring/core/annotations/bean-lifecycle.png
        :align: center


PostConstruct
-------------
- annotation used to performe business logic on bean initialization, like creating db connections


    .. code-block:: python
        :linenos:

        interface Coach {
            String getDailyWorkout();
        }

        @Component
        class CricketCoach implements Coach {
            @Override
            public String getDailyWorkout() {
                return "Cricket coach daily workout";
            }

            @PostConstruct
            public void doMyStartupStuff() {
                System.out.pringln("In doMyStratupStuff()");
            }
        }


PreDestroy
----------
- annotation used to performe business logic on bean destroy phaze, like stopping db connections
- this methods is not invoked for PROTOTYPE bean scope !!!!!
    - Spring does not manage the complete lifecycle of a prototye bean, it is handled only the initialization part


    .. code-block:: python
        :linenos:

        interface Coach {
            String getDailyWorkout();
        }

        @Component
        class CricketCoach implements Coach {
            @Override
            public String getDailyWorkout() {
                return "Cricket coach daily workout";
            }

            @PreDestroy
            public void doMyCleanupStuff() {
                System.out.pringln("In doMyCleanupStuff()");
            }
        }



:ref:`Go Back <frameworks-java-spring-core-annotation-label>`.
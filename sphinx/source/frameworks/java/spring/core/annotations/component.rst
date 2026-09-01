.. _frameworks-java-spring-core-annotation-component-label:

Autowiring
==========
- for dependency injection, Spring can use autowiring
- Spring will look for a class that matches:
    - match by type: class or interface:
        - Spring will inject it automatically
        - Spring will scan for @Components anotation and in case it founds an object which is of this class (or interface), will inject it
- in case you have only one constructor, @Autowire annotation is optional, but is recommended to be added



:ref:`Go Back <frameworks-java-spring-core-annotation-label>`.

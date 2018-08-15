.. _architecture-basic-label:

Architecture Basics
===================

What makes a good software ?
----------------------------
- a good software:
    - is robust:
        - is resistant to changes around it and failures
        - it recognize and deal with failures
    - is measurable:
        - it should be possible to see how well the code is doing outside of a test environment
    - is debuggable:
        - to be able to switch to debug mode which will log more information
    - is maintainable:
        - a software should be easy to maintain if it has consistent styling, good comments (when needed), is modular
    - is extensible:
        - a software should be easy to extend with new functionalities
    - is secure

How to do it ?
--------------
- make sure the client needs the new functionality
    - by asking WHY he needs it and not HOW to do it
- define the API
    - is very important to have a good API in any system (especially in a distributed system)
        - a weak API can modify the entire software architecture
- single Responsibility
    - a responsibility is a reason to change
    - a class should have only one reason to change
    - example: consider a class that compiles and prints report. Such a class can be changed for 2 reasons.
      First, the content of the report, second, the format of the report. These are changes for 2 different causes,
      one of the content, one cosmetic
- high cohesion
    - cohesion is the degree to which the elements of a certain module belong together
    - it measure how strongly related each piece of functionality expressed by the source code of a class is
    - it can be of different types:
        - coincidental cohesion (worst)
            - when parts of a module are grouped arbitrarily
            - the only relationship between the parts is that they have been grouped together
            - example : Utility.class where elements are completely independent of each other

                .. image:: ../images/oop/high-cohesion-1.png
                    :align: center

        - logical cohesion:
            - parts of modules are grouped because they are logically categorized to do the same thing,
              even if they are different by nature
            - ex : grouping all mouse and keyboard input handling routines
        - functional cohesion (best):
            - parts of module are grouped because they all contribute to a single well-defined task of the module

                .. image:: ../images/oop/high-cohesion-2.png
                    :align: center

- loose coupling
    - coupling is how much one component knows about the inner workings or inner elements of another one
    - tight coupling : is where components are so tied to one another, that you cannot possibly change one without
      changing the other

    .. image:: ../images/oop/tight-couplings-1.png
        :align: center

    - loose coupling : is a method of interconnecting the components in a system or network so that those components depend
      on each other to the least extent practically possible

    .. image:: ../images/oop/tight-couplings-2.png
        :align: center

- use interfaces
    - an interface declares a contract
    - implementing an interface enforces that your class will be bound to the contract
    - everything that relies on that contract can work with your object.
    - if you "program to an interface not an implementation", then you can inject different objects which share the
      the same interface into the method as an argument
    - this will lead you to Open/Closed principal
    - a common misconception is extracting interface for every class and using those interface everywhere instead of
      using the class directly -> will add unnecessary maintenance overhead for single, specific classes
    - a good rile is to only create interfaces when the class has more than one implementation
- dependency injection
    - refers when a third component provides the concrete implementation of the contract for the needed component

    .. image:: ../images/oop/dependency-injection1.png
        :align: center

    - in the above example, if we decide later that we want to use another implementation of the service, it will
      be very hard as Foo class shouldn't bother itself with the details of choosing the specific implementation

    .. image:: ../images/oop/dependency-injection2.png
        :align: center

    - now, the Bar class has the problem of filling in Foo's extra dependency

    .. image:: ../images/oop/dependency-injection3.png
        :align: center

    - and class Bar probably also doesn't care about what specific implementation of Service Foo uses. Therefore,
      we push the dependency up again

    .. image:: ../images/oop/dependency-injection4.png
        :align: center

    - benefits:
        - testability
        - refactorability -> changes can be done easier
        - encourages modular code -> it force you to think about the contract between classes
    - drawbacks:
        - for small enough projects is not suitable, it can be used singleton

- Bibliography:
    - https://thebojan.ninja/2015/04/08/high-cohesion-loose-coupling/
    - https://www.codementor.io/learn-development/what-makes-good-software-architecture-101

:ref:`Go Back <oop-label>`.
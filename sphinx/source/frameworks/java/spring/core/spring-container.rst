.. _frameworks-java-spring-core-spring-container-label:

Spring container
================

Inversion of Control (IoC)
--------------------------
- is the approach of outsourcing the construction and management of objects

General
-------
- is an object factory
- based on configuration, it can determine which object to retrieve
- has 2 primary functions:
    - create and manage objects (Inversion of Control)
    - inject object dependencies (Dependency Injection)
- there are 3 different approaches to configure Spring Container:
    - XMl configuration file (legacy)
    - Java Annotations
    - Java Source Code


Dependency Injection
--------------------
- use the dependency inversion principal
- the client delegates to another object the responsabilitiy of providing its dependencies (injecting all the needed internal dependencies and helper components for a given object)


:ref:`Go Back <frameworks-java-spring-core-label>`.
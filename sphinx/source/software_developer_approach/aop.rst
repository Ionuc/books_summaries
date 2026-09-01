.. _software-developer-approach-aop-label:


Aspect-Oriented Programming (AOP)
=================================
- it separates system-wide tasks, like logging, security and error tracking from code business logic
- prevents developers from repeating the same code across multiple classes
- is a programmig technique based on concept of an Aspect

Concepts
--------

- Aspect
    - module of code for cross-cutting concerns
    - encapsulate cross-cutting logic / concern / functionality
    - can be reused at multiple locations
    - can be applied on different parts of system based on configuration
- JointPoint
    - a specific palce in program's execution, like method call or an exception being thrown, where extra behavior can be plugged in
- Pointcut
    - a rule or expression that selects which join points should actually trigger the extra code
- Advice
    - the actual code that runs when a pointcut matches a join point
- Weaving:
    - the process of combining aspects with your main application code
    - can happen at compile-time, load-time or runtime

Advice types
------------
- before advice
    - run before the method
- after finally advice:
    - run after the method (loke finally block)
- after returning advice:
    - run after the method if method was executed with success
- after throwing advice:
    - run after method if exception was thrown
- around advice:
    -run before and after method

Benefits
--------
- code for aspect is defined in a single class
    - code is not duplicated in all components
    - promotes code reuse
- buisness code in your application is cleaner
- configurable

Disadvantages
-------------
- in case of too many aspects, the flow is hard to follow
- minor performance cost

AOP Uee Cases
-------------
- most common:
    - logging
    - security
    - transactions
- Audit logging:
    - who, what, when, where
- Exception handling
    - log exception and notify DevOps team
- API Management (Metrics)
    - how many times has a method been called



:ref:`Go Back <software_developer_approach-label>`.
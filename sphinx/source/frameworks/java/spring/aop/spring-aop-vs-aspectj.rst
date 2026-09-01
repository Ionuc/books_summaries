.. _frameworks-java-spring-aop-vs-aspectj-label:

Spring AOP vs Aspectj
=====================
- Spring AOP
    - Spring provides AOP support
    - is key component of Spring
        - Security, transaction, caching are already implemented using AOP
    - use run-time waaving of aspects with run-time implementation
    - use proxy patternto advice an object
    - simpler to use than AspectJ
    - can migrate to AspectJ when using @Aspect annotation
    - disadvantage:
        - supports only method-level jin points
        - can only apply aspects to beans created by Spring app cntext
        - minor performance cost for aspect execution because of run-time weaving

- AspectJ
    - is the original AOP framework, released in 2001
    - provides complete support of AOP
    - wich support for:
        - join points: method-level, constructor, field
        - code weaving: compile-time, post compile-time and load-time
    - works with any POJO, not just beans from app context
    - faster performance compared to Spring AOP
    - complete AOP support
    - disadvantage:
        - compile time is longer
        - can become complex


:ref:`Go Back <frameworks-java-spring-aop-label>`.

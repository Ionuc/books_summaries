.. _frameworks-java-spring-aop-annotations-before-label:

Before
======
- used to peforme a logic before creating the bean
- most common use-cases:
    - logging
    - security
    - transaction:
        - @Transaction annotation uses AOP to performe all logic inside the same transaction
- audit logging
- metrics

    .. code-block:: python
        :linenos:

        @Aspect
        Order(1)
        @Component
        pulic class MyDemoAspect1 {
            @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
            private void forDaoPackage(){};

            @AfterReturning("forDaoPackage()")
            public void afterAddAccountAdvice() {
               ... // do the logic
            }
        }


:ref:`Go Back <frameworks-java-spring-aop-annotation-label>`.

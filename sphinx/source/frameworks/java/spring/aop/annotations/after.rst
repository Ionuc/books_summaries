.. _frameworks-java-spring-aop-annotations-after-label:

After
=====
- used to peforme a logic after the target method was completed, regardless of outcome (success or failure)
- works just like finally block from try-catch
- in case of exception, the exception will be propagated to AOP Proxy and then to Main App
- you don't have access to the exception

    .. code-block:: python
        :linenos:

        @Aspect
        Order(1)
        @Component
        pulic class MyDemoAspect1 {
            @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
            private void forDaoPackage(){};

            @After("forDaoPackage()")
            public void afterAddAccountAdvice(JoinPoint joinPoint) {
               ... // do the logic
            }
        }


:ref:`Go Back <frameworks-java-spring-aop-annotation-label>`.

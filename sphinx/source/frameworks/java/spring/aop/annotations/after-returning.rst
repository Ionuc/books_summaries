.. _frameworks-java-spring-aop-annotations-after-returning-label:

AfterReturning
===============
- used to peforme a logic after the target method was called with success and method was exit


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


Access Return value
-------------------
- you can access the return value of the target method by:
    - setting "returning" to a variable
    - define parameters JoinPoint & variable 

    .. code-block:: python
        :linenos:

        @Aspect
        Order(1)
        @Component
        pulic class MyDemoAspect1 {
            @Pointcut("execution(* com.luv2code.aopdemo.dao.*.findAccounts(..))")
            private void forDaoPackage(){};

            @AfterReturning(
                pointcut="forDaoPackage()",
                returning="result"
            )
            public void afterAddAccountAdvice(JoinPoint joinPoint, List<Account> result) {
               ... // do the logic with result
            }
        }

Post-processing data
--------------------
- you can post process the data before returning the caller
- format the data or enrich the data
- you should be very carefull about this feature!

    .. code-block:: python
        :linenos:

        @Aspect
        Order(1)
        @Component
        pulic class MyDemoAspect1 {
            @Pointcut("execution(* com.luv2code.aopdemo.dao.*.findAccounts(..))")
            private void forDaoPackage(){};

            @AfterReturning(
                pointcut="forDaoPackage()",
                returning="result"
            )
            public void afterAddAccountAdvice(JoinPoint joinPoint, List<Account> result) {
               ... // do the logic with result
               // modify "result" list: add, remove, update, etc
               if (!restult.isEmpty()) {
                    Account account = result.get(0);
                    account.setName("Ionut Mesaros");
               }
            }
        }


:ref:`Go Back <frameworks-java-spring-aop-annotation-label>`.

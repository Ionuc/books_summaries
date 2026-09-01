.. _frameworks-java-spring-aop-annotations-after-throwing-label:

AfterThrowing
=============
- used to peforme a logic after the target method was called and exception was thrown
- at this point, you just have access to the exception, but the exception will be still propagated to the AOP Proxy component and then to Main App


    .. code-block:: python
        :linenos:

        @Aspect
        Order(1)
        @Component
        pulic class MyDemoAspect1 {
            @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
            private void forDaoPackage(){};

            @AfterThrowing("forDaoPackage()")
            public void afterThrowingAddAccountAdvice() {
               ... // do the logic
            }
        }

Use Cases
---------
- log exception
- perform auditing on the exception
- notify DevOps team

Access Excption value
---------------------
- you can access the the exception thrown by target method by:
    - setting "throwing" to a variable
    - define parameters JoinPoint & variable 

    .. code-block:: python
        :linenos:

        @Aspect
        Order(1)
        @Component
        pulic class MyDemoAspect1 {
            @Pointcut("execution(* com.luv2code.aopdemo.dao.*.findAccounts(..))")
            private void forDaoPackage(){};

            @AfterThrowing(
                pointcut="forDaoPackage()",
                throwig="exception"
            )
            public void afterThrowingAddAccountAdvice(JoinPoint joinPoint, Throwable exception) {
               ... // do the logic
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

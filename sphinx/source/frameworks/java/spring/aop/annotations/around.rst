.. _frameworks-java-spring-aop-annotations-around-label:

Around
======
- used to performe some logic before and after calling the target method
- is like a combination of @Before & @After
- you can stop propagation of exception
- you can have access to ProceedingJoinPoint:
    - it is used to execute the target method


    .. code-block:: python
        :linenos:

        @Aspect
        Order(1)
        @Component
        pulic class MyDemoAspect1 {
            @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
            private void forDaoPackage(){};

            @Around("forDaoPackage()")
            public Object aroundAddAccountAdvice(ProceedingJoinPoint proceedingJoinPoint) {
               ... // do the logic
               long startTime = System.currentTimeMillis();

               Object result = proceesingJoinPoint.proceed(); // will call the target method

               long end = System.currentTimeMillis();
               System.out.println("Duraction " + (end - start));

               return result;
            }
        }

Use cases
---------
- logging, auditing, security
- pre-processing and post-processing data
- instrumentation:
    - metrics about how long it took for target method to run
- managing exceptions:
    - swallog / handle / stop exception propagation

Handling exceptions
-------------------
- you can handle, or stopping the exception
- in order to stop exception, you wrap it into try-catch block
- in case you want to retrow it, then throw it


    .. code-block:: python
        :linenos:

        @Aspect
        Order(1)
        @Component
        pulic class MyDemoAspect1 {
            @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
            private void forDaoPackage(){};

            @Around("forDaoPackage()")
            public Object aroundAddAccountAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Exception{
               ... // do the logic
               long startTime = System.currentTimeMillis();

               try {
                   Object result = proceesingJoinPoint.proceed(); // will call the target method
               } catch (Exception e) {
                    System.out.println("Exception: " + e)
                    return DEFAULT_VALUE; // return default value of rethrow exception
               }

               long end = System.currentTimeMillis();
               System.out.println("Duraction " + (end - start));

               return result;
            }
        }


:ref:`Go Back <frameworks-java-spring-aop-annotation-label>`.

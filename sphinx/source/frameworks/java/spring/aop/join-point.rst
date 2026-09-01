.. _frameworks-java-spring-aop-join-point-label:

JoinPoint
=========
- is used to get methods metadata:
    - signiture
    - arguments

- methods:
    - getSignature():
        - return the MethodSignature object
    - getArgs():
        - return an array of the args of type Object[]


    .. code-block:: python
        :linenos:

        @Aspect
        @Component
        pulic class MyDemoAspect {
            @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
            private void forDaoPackage(){};

            @Before("forDaoPackage()")
            public void beforeAddAccountAdvice(JointPoint joinPoint) {
                MethodSignature methodSig = (MethodSignature) joinPoint.getSignature()

                // get args
                Object[] args = joinPoint.getArgs();
                for (Object arg : args) {
                    System.out.println(arg);
                }
            }

        }


:ref:`Go Back <frameworks-java-spring-aop-label>`.

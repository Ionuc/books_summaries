.. _frameworks-java-spring-aop-annotations-order-label:

Order
=====
- used to specify in which order multiple aspects will be executed for the same method call
- the lower number has higher priority
- range number : Integer.MIN_VALUE -> Integer.MAX_VALUE
- negative numbers are allowed
- does not have to be consecutive
- in case multiple aspects are having same order number, they will be taken in undefine order


    .. code-block:: python
        :linenos:

        @Aspect
        Order(1)
        @Component
        pulic class MyDemoAspect1 {
            @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
            private void forDaoPackage(){};

            @Before("forDaoPackage()")
            public void beforeAddAccountAdvice() {
               ... // do the logic
            }
        }

        @Aspect
        Order(2)
        @Component
        pulic class MyDemoAspect2 {
            @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
            private void forDaoPackage(){};

            @Before("forDaoPackage()")
            public void beforeAddAccountAdvice() {
               ... // do the logic
            }
        }

        // the order executed will be : MyDemoAspect1 & MyDemoAspect2


:ref:`Go Back <frameworks-java-spring-aop-annotation-label>`.

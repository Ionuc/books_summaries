.. _frameworks-java-spring-aop-pointcut-label:

Pointcut
========
- Spring AOP uses AspectJ's pointcut expression language
- types of pointcuts:
    - execution:
        - applies to execution of methods
        - it matches on method name
        - basci layout : execution(modifiers-pattern? return-type-pattern declaring-type-patterns? method-name-pattern(param-pattern) throws-pattern):
            - modifier-pattern?:
                - modifiers: public, protected and package-protected
            - return-pattern:
                - refers to the return type of the method
            - declaring-type-pattern?:
                - refers to the class name
            - method-name-pattern:
                - refers to the method name
            - param-pattern:
                - refers to the method parameter
            - thrown-pattern:
                - match on exception throws
        - the pattern is optional if has the "?"

Pointcut declaration
--------------------
- you can declare only onace a Pointcut and reuse them in any advice
- it is defined as method with no argument and no body, but annotated with @Poitcut


    .. code-block:: python
        :linenos:

        @Aspect
        @Component
        pulic class MyDemoAspect {
            @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
            private void forDaoPackage(){};

            @Before("forDaoPackage()")
            public void beforeAddAccountAdvice() {
               ... // do the logic
            }

            @Before("forDaoPackage()") // reuse the same poitcut declaration
            public void performeApiAnalytics() {
               ... // do the logic
            }
        }
        

- Combining pointcuts:
    - you can combine pointcut declarations using logical operators:
        - AND (&&):
            @Before("expressionOne() && expressionTwo()")
        - OR (||):
            @Before("expressionOne() || expressionTwo()")
        - NOT (!):
            @Before("expressionOne() && !expressionTwo()")
    - the execution happen only if the evaluation is true
    - example: apply for all metthods instead of Getters and Setters:


    .. code-block:: python
        :linenos:

        @Aspect
        @Component
        pulic class MyDemoAspect {
            @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
            private void forDaoPackage(){};

            @Pointcut("execution(* com.luv2code.aopdemo.dao.*.get(..))")
            private void getter(){};

            @Pointcut("execution(* com.luv2code.aopdemo.dao.*.set(..))")
            private void setters(){};

            @Pointcut("forDaoPackage() && !getter() && !setter()")
            private void combined(){};

            @Before("combined()")
            public void beforeAddAccountAdvice() {
               ... // do the logic
            }

            @Before("forDaoPackage()")
            public void performeApiAnalytics() {
               ... // do the logic
            }
        }

Examples
--------
    - match on method names:
        - match only on AddAccount() method in AccountDAO class:
            - @Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())"):
                - modifier pattern: publi
                - return type pattern: void
                - declaring type pattern: com.luv2code.aopdemo.dao.AccountDAO
                - method anme pattern: addAccount()
        - match any addAccount() method in any class:
            - @Before("execution(public void addAccount())")
        - match methods starting with "add" in any class, using wildcards:
            - @Before("execution(public void add*())")
        - use wildcards on return type
            - @Before("execution(public * procesCreditCard*())"):
                - return type pattern: *
                - method name pattern: processCreditCard*()
    - match method name with paramters:
        - () - matches a method with no argument:
            - @Before("execution(public void addAccount())")
        - (*) - matches a method with one argument of any type
            - @Before("execution(public void addAccount(*))")
        - (..) - matches a method with 0 or more arguments of any type
            - @Before("execution(public void addAccount(..))")
        - match addAccount(Account account):
            - needs the fuly qualify class name 
            - @Before("execution(public void addAccount(com.luv2code.aopdemo.Account))")
        - match addAccount with first parameter Account and maybe other parameters:
            - needs the fuly qualify class name
            - ".." can be used after the first parameter to match more parameters if found
            - @Before("execution(public void addAccount(com.luv2code.aopdemo.Account, ..))")
    - match on methods only in the a specific package with any nr of arguments
        - @Before("execution(public void com.luv2code.aopdemo.dao.*.*(..))"):
            - return type pattern: void
            - package pattern: com.luv2code.aopdemo.dao
            - class name pattern: .*
            - method pattern: .*
            - parameter pattern: (..)


:ref:`Go Back <frameworks-java-spring-aop-label>`.

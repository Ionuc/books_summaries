.. _frameworks-java-spring-aop-annotations-aspect-label:

Aspect
======
- used to create the aspect component
- is just a java class that has a collection of related advices (before or after or etc)

- example of BEFORE:
    - @Before can be used to target object method with signiture: "public void addAccount()"
    - the pointcut expression is "execution"

    .. code-block:: python
        :linenos:


        public interface AccountDAO {
            void addAccount();
        }

        @Repository
        public class AccountDAOImpl implements AccountDAO {
            @Override
            public void addAccount() {
                System.out.println("Inside AccountDAOImpl")
            }
        }

        @SpringBootApplication
        public class AopDemoApplication {
            public static void main(String[] args) {
               SpringApplication.run(AopDemoApplication.class);
            }

            @Bean
            public CommandLineRunner commandLineRunner(AccountDAO accountDAO) {
                return runner -> {
                    demoTheBeforeAdvice(accountDAO);
                    System.out.println("Hello World")
                }
            }

            private demoTheBeforeAdvice(AccountDAO accountDao) {
                accountDAO.addAccount();
            }
        }

        @Aspect
        @Component
        public class MyDemoAspect {
            @Before("execution(public void addAccount())")
            public void beforeAddAccountAdvice() {
                System.out.prinln(" Code executed before")
            }
        }


Order of advices
----------------
- in case multiple adivices are matched for the same method call, the order in which they will be executed is undefined
- if you want the gurantee that aspects are executed in a certain order, then you need to:
    - defined eahc pointcut in its own aspect
    - apply the @Order annotation on each created aspect

:ref:`Go Back <frameworks-java-spring-aop-annotation-label>`.

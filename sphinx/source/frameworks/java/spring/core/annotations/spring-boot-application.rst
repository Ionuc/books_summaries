.. _frameworks-java-spring-core-annotation-spring-boot-application-label:

SpringBootApplication
=====================
- is used on the class which is having the main() method
- it extends:
    - @SpringBotConfiguration:
        - specify that the corresponding file is the spring boot config file

    - @EnaleAutoConfiguration:
        - auto configuration
        - spring will try to make a quess on what would be the ideal configuration based on dependencies defined in pom.xml
        - DisaptacherServler is handled automatically by Spring Boot, also EntityManager, DataSource in case of Hibernate
    - @ComponentScan:
        - enable component scanning all the beans of current package
        - recursively scans sub-packages from current package
    - @Configuration:
        - able to register extra beans with @Bean or import other configuration classes




    .. image:: ../../../../../images/frameworks/java/spring/core/annotations/spring-boot-application-multiple-base-package.png
        :align: center


:ref:`Go Back <frameworks-java-spring-core-annotation-label>`.

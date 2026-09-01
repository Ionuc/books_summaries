.. _frameworks-java-spring-label:

Spring frameworks
=================

Before Spring Boot
------------------
- when creating a new project, you need to:
    1) create a maven / gradle project
    2) find the required dependencies and add them in POM.xml
    3) download Tomcat Server, install it and configure in IDE
    4) download and install Database Software and run it
    5) Write your appliction logic
    6) Create a config file defining all the beans(Dispatcher servlet, View Resolver, etc)
    7) If there is DB, configure DB parameters, DAtasource, EntityManager
    8) Build, Deploy the artifact on the server and run it

After Spring Boot
-----------------
- you can focus only on Step 5: Write your appliction logic


.. toctree::
    :maxdepth: 2
    :caption: Contents:

    core/index.rst
    rest/index.rst
    data-jpa/index.rst
    data-rest/index.rst
    security/index.rst
    doc/index.rst
    aop/index.rst
    mvc/index.rst
    thymeleaf/index.rst
    actuator/index.rst
    webflux/index.rst
    restclient/index.rst
    cloud/index.rst
    kafka/index.rst


:ref:`Go Back <frameworks-java-label>`.
.. _java-development-jdbc-jdbc-label:

JDBC
====
- stands for Java Database Connectivity
- is a standard Java API for database
- allows Java programs to send SQL statements to a database
- each provide of relation database managemetn system provides own implementation of JDBC driver
- so, jdbc API is just a standard set of APIs interfaces and JDBC driver is a concrete implementation

Driver types
------------
- Driver Type 1:
    - contains a mapping to another data access API
    - is a database driver implementation that employs of ODBC (Open Database Connectivity) driver to connect to the database
    - converts JDBC methods calls into ODBC function calls
    - ODBC:
        - stands for Open Datbase Connectivity
        - is a standard aplication programming interface for accessing database management system
        - aimed to make independent of database system and operating systems
        - aplication written using ODBC ca be ported to other platforms, both on client and server side
        - this driver is platform dependent, which in turn depends on native libraries
        - advantages:
            - almost any database for which an ODBC driver is installed can be accessed and data can be retrieved
        - disadvantages:
            - ODBC driver needs to be installed on client side
            - performance overhead since the calls have to go through the JDBC bridge to the ODBC driver then to the native database connectivity interface
            - specific ODBC drivers ar not always available on all platforms
            - no support from Java 8

- Driver Type 2:
    - is an implementation that uses client side libraries of the target database
    - is also called native API driver
    - driver converts JDBC methods calls into native calls of the database API
    - for example: Oracle Aussie Driver is a type 2 driver
    - advantages:
        - better performance than Driver type 1
        - the vendor client library needs to be installed on the client machine
        - not all databases have a client-side library
        - this driver is platform dependent

- Driver Type 3:
    - use middleware to convert JDBC calls into database specific calls,
    - also known as a network protocol driver
    - advantages:
        - since the communication between client and the middleware server is database independent, there is no need for the database vendor library on the client
        - the client will not change for a new database
        - the middleware server can provide typical middleware services likes caching of connections, query results, etc, load balancing, logging and auditing
        - a single driver can handle any database, provided the middleware supports it
    - disadvantages:
        - require database-specific coding to be done in the middle tier
        - the middleware layer added may result in additional latency, but is typically overcome by using better middleware services
- Driver Type 4:
    - connects directly to database by converting JDBC calls into database specific calls
    - they are install in JAva virtual machine on client side
    - advantages:
        - completely implemented in Java to achieve platform independence
        - these drivers don't translate the request into an intermediary format, such as ODBC
        - the client application connects directly to the database server
        - the JVM can manage all aspects of the application-to-database connection
    - disadvantages:
        - drivers are database specific


DriverManager
-------------
- is responsible for managing JDBC drivers
- is used also to create a connection to database which needs to be properly closed:
    - host & credentials needs to be passed
    - host is composed of :
        - suffix : 
            - contains the database name
            - example:"jdbc:mysql"
        - url:
            - url to connect to having host & port
            - example: localhost:3306/



    .. code-block:: python
           :linenos:

            try (Connection connection = DriverManager.getConnection(JDBC_MYSQL_HOST + DB_NAME, USERNAME, PASSWORD);) {

                if (connection != null) {
                    System.out.println("You made it, take control your database now!");
                } else {
                    System.out.println("Failed to make connection!");
                }

            } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
                e.printStackTrace();
                return;
            }



:ref:`Go Back <java-development-jdbc-label>`.
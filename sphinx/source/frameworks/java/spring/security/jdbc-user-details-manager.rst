.. _frameworks-java-spring-security-jdbc-user-details-manager-label:

JdbcUserDetailsManager
==========================
- used to retrieve UserDetails from a DB
- you can use Spring Security's predefined table schema or your custom schema for getting user, passwrod & roles
- will query the DB for each request


Default schema
--------------
- Spring Security provides default table schema as:
    - users:
        - username VARCHAR (50)
        - password VARCHAR (50)
        - enable TINYINT (1)
    - authorities:
        - username VARCHAR(50)
        - authority VARCHAR(50)


- Steps
    1. create SQL scripts:
        - internally, Spring will store:
            - the password in format "<encodingAlgorithmId><encodedValue>" in `users`
            - the authority value with prefix "ROLE_"

    .. code-block:: python
        :linenos:

        CREATE TABLE `users` (
            `username` varchar(5) NOT NULL,
            `password` carchar(5) NOT NULL,
            `enabled` tinyint NOT NULL,
            PRIMARY KEY (`username`)
        ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

        INSERT INTO `users` VALUES
        ('john', '{noop}test123'),
        ('marry', '{noop}test123'),
        ('susan', '{noop}test123');

        CREATE TABLE `authorities` (
            `username` varchar (50) NOT NULL,
            `authority` varchar(50) NOT NULL,

            UNIQUE KEY `auhtorities_idx_1` (`username`, `authority`),
            CONSTRAINT (`auhtorities_idx_1`)
            FOREIGN_KEY (`username`)
            REFERENCES `users` (`username`)
        ) ENGINE=InnoDB DEFAULT CHARSET=latin1

        INSERT INTO `authorities` VALUES
        ('john', 'ROLE_EMPLOYEE'),
        ('marry', 'ROLE_EMPLOYEE'),
        ('marry', 'ROLE_MANAGER'),
        ('susan', 'ROLE_EMPLOYEE'),
        ('susan', 'ROLE_MANAGER'),
        ('susan', 'ROLE_ADMIN');


    2. Add Database support to Maven / Gradle file:
        - add db artifact dependency

    3. Create JDBC properties file


    .. code-block:: python
        :linenos:

        spring.datasource.url=<url>
        spring.datasource.username=<username>
        spring.datasource.password=<password>

    4. Update Spring Security to use JDBC

    .. code-block:: python
        :linenos:

        @Configuration
        public class DemoSecurityConfig {
            @Bean
            public UserDetailsManager userDetailsManager(DataSource dataSource) {
                return new JdbcUserDetailsManager(dataSource);
            }
        }


Custom schema
-------------
- you can use your custom table
- you need to set:
    - the SQL query for retrieving user details
    - the SQL query for retrieving authorities
- the "?" from query is just a label used for paramter binding


    .. code-block:: python
        :linenos:

        CREATE TABLE `members` (
            `user_id` varchar(5) NOT NULL,
            `pwd` carchar(5) NOT NULL,
            `active` tinyint NOT NULL,
            PRIMARY KEY (`user_id`)
        ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

        INSERT INTO `members` VALUES
        ('john', '{noop}test123'),
        ('marry', '{noop}test123'),
        ('susan', '{noop}test123');

        CREATE TABLE `roles` (
            `user_id` varchar (50) NOT NULL,
            `role` varchar(50) NOT NULL,

            UNIQUE KEY `roles_idx_1` (`user_id`, `role`),
            CONSTRAINT (`roles_idx_1`)
            FOREIGN_KEY (`user_id`)
            REFERENCES `members` (`user_id`)
        ) ENGINE=InnoDB DEFAULT CHARSET=latin1

        INSERT INTO `roles` VALUES
        ('john', 'ROLE_EMPLOYEE'),
        ('marry', 'ROLE_EMPLOYEE'),
        ('marry', 'ROLE_MANAGER'),
        ('susan', 'ROLE_EMPLOYEE'),
        ('susan', 'ROLE_MANAGER'),
        ('susan', 'ROLE_ADMIN');


    .. code-block:: python
        :linenos:

        @Bean
        public UserDetailsManager userDetailsManager(DataSource dataSource) {

            JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

            // define query to retrieve a user by username
            jdbcUserDetailsManager.setUsersByUsernameQuery(
                    "select user_id, pw, active from members where user_id=?");

            // define query to retrieve the authorities/roles by username
            jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                    "select user_id, role from roles where user_id=?");

            return jdbcUserDetailsManager;
        }

:ref:`Go Back <frameworks-java-spring-security-label>`.
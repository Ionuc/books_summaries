.. _frameworks-java-spring-security-general-label:

General
=======
- Spring Security defines a framework for security
- is implemented using Servlet filters in the background
- 2 ways of securing an app:
    - declarative:
        - define application's security constraints in configuration
            - all Java config are in component having @Configuration annotations
        - separates security code from application code
    - programmatic:
        - Spring Security provides an API for custom application coding
        - provides greater customization for specific app requirements
- when request are trying to access some endpoint, Spring Security will:
    - check appplication configuration
    - retrieve users, passwords and roles to check if the user should continue or not

Servlet Filters
---------------
- are used to pre-process / post-process web requests
- can route web requests based on security logic

Concepts
--------
- Authentication:
    - check user id & password with credentials stored in app / db
- Authorization:
    - check to see if user has an authorized role

Enable Spring Security
----------------------
- add dependency : "Spring-boot-starter-security"
- all endpoints for application are secured automatically
- default user: "user"
- default password: is logged in console for "Using generated security password: <value>"
- you can override default user & password by setting application configs:
    - spring.security.user.name=ionut
    - spring.security.user/password=ionut
- by default, Spring will use Basic Authentication (user & password)


Login Process
-------------
1. Retrieve password from DB for user
2. Read the encoding algorithm id (noop sau bcrypt)
3. For case of encoding algorithm id different than noop (plain text), encrypt plaintext password from login form (for bcrypt using salt from db password)
4. compare encrypted password from login form with password from DB
5. if there is a match, login successful

-IMPORTANT:
    - password from DB is never decrypted, because for bcyrpt, it is a one-way encryption

Examples
--------
- example using in-momery users:
    - passwords are stored as "{noop}<value>"

    .. code-block:: python
        :linenos:


        @Configuration
        public class DemoSecurityConfig {

            @Bean
            public InMemoryUserDetailsManager userDetailsManager() {

                UserDetails john = User.builder()
                        .username("john")
                        .password("{noop}test123")
                        .roles("EMPLOYEE")
                        .build();

                UserDetails mary = User.builder()
                        .username("mary")
                        .password("{noop}test123")
                        .roles("EMPLOYEE", "MANAGER")
                        .build();

                UserDetails susan = User.builder()
                        .username("susan")
                        .password("{noop}test123")
                        .roles("EMPLOYEE", "MANAGER", "ADMIN")
                        .build();

                return new InMemoryUserDetailsManager(john, mary, susan);
            }
        }


- example using DB users:


    .. code-block:: python
        :linenos:

        @Configuration
        public class DemoSecurityConfig {

            // add support for JDBC ... no more hardcoded users :-)

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


            @Bean
            public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
                );

                // set custom form login & logout in case Spring MVC is used
                http.formLogin(
                        form -> form.loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authentucateTheUser")
                                .permitAll())
                    .logout(logout -> logout.permitAll()); // clear web browser session


                // use HTTP Basic authentication
                http.httpBasic(Customizer.withDefaults());

                // disable Cross Site Request Forgery (CSRF)
                // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
                http.csrf(csrf -> csrf.disable());

                return http.build();
            }
        }


:ref:`Go Back <frameworks-java-spring-security-label>`.
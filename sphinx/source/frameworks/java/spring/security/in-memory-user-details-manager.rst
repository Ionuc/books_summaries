.. _frameworks-java-spring-security-in-memory-user-details-manager-label:

InMemoryUserDetailsManager
==========================
- used to store UserDetails object in memory
- mostly used for testing


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



:ref:`Go Back <frameworks-java-spring-security-label>`.
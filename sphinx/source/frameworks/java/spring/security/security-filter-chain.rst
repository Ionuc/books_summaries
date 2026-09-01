.. _frameworks-java-spring-security-security-filter-chain-label:

SecurityFilterChain
===================
- acts as the entry point for all incoming HTTP requests in Spring Security
- every request passes through a chain of filters such as UsernamePasswordAuthenticaionFilter and BasicAuthenticationFilter
- handles:
    - authentication
    - authorization
    - CSRF protection
    - session Management
- is used to do authorization
- is used to specify which user can access which endpoint
- methods from AbstractRequestMatcherRegistry can be used to specify:
    - endpoint name
    - http methods (optionally)
    - single role or multiple roles

- to match multiple endpoints, you can use "**", like:
    - "/api/employees/**"     


    .. code-block:: python
        :linenos:

        @Configuration
        public class DemoSecurityConfig {

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

                // use HTTP Basic authentication
                http.httpBasic(Customizer.withDefaults());

                // disable Cross Site Request Forgery (CSRF)
                // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
                http.csrf(csrf -> csrf.disable());

                return http.build();
            }

        }



:ref:`Go Back <frameworks-java-spring-security-label>`.
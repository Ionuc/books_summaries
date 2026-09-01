.. _frameworks-java-spring-security-csrf-label:

Cross-Site Request Forgery (csrf)
=================================
- Spring security can protect against CSRF attacks
- embed additional authentication data/tolen into all html forms
- on subsequent requetst, web app will verify token before processing
- used mostly in traditional web applications (HTML forms, etc)

- is recommended to use for any normal browser web requests
- it is optional in case the client is a non-browser client or stateless REST APIs

How to Disable
--------------


    .. code-block:: python
        :linenos:

        @Configuration
        public class DemoSecurityConfig {

            @Bean
            public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                // disable Cross Site Request Forgery (CSRF)
                // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
                http.csrf(csrf -> csrf.disable());

                return http.build();
            }
        }



:ref:`Go Back <frameworks-java-spring-security-label>`.
.. _frameworks-java-spring-actuator-general-label:

General
=======
- spring library name: spring-boot-starter-actuator

- use-cases:
    - monitoring our app
    - gathering metrics
    - understanding traffic or the state of our database

- the actuator mainly exposes operational information about the running application — health, metrics, info, dump, env, etc
- it uses HTTP endpoints or JMX beans to enable us to interact with it
- once this dependency is on the classpath, several endpoints are available for us out of the box

- only 2 endpoints are by default enables:
    - /health
    - /info
- if we want to enable all of them, we could set management.endpoints.web.exposure.include=*.
- if Spring Security is used, then it is enough just to add requestMatcher:


    .. code-block:: python
        :linenos:

        @Bean
        public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
            return http
              .authorizeHttpRequests(auth -> auth
                .requestMatchers("/actuator/**").permitAll() // allow the access to all clients
                .anyRequest().authenticated())
              .build();
        }


Commonly Endpoints
------------------
- bellow are common endpoints used:
    - beans:
        - Displays a complete list of all the Spring beans in your application.
    - caches:
        - Exposes available caches.
    - conditions:
        - Shows conditions evaluated on configuration and auto-configuration classes.
    - health: 
        - Shows application health information.
    - httptrace:
        - Displays HTTP trace information (last 100 requests).
    - loggers:
        - Shows and modifies the configuration of loggers in the application.
    - mappings:
        - Displays a collated list of all @RequestMapping paths.
    - sessions:
        - Retrieves and deletes user sessions (requires Spring Session).
    - threaddump:
        - Performs a thread dump.


:ref:`Go Back <frameworks-java-spring-actuator-label>`.

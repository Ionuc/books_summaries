.. _frameworks-java-spring-cloud-gateway-route-locator-label:

RouteLocator
============
- is used to do a redirect of existing request to another URL
- all clients can send request only to Spring Cloud Gateway and all requests will be redirect to corresponding microservice
    

    .. code-block:: python
        :linenos:

        //application.properties
        spring.application.name=gateway-service

        // config component
        @Configuration
        public class SrpingCloudConfig {
            @Bean
            public RouteLocator buildRouteLocator(RouteLocatorBuilder builder) {
                return builder.routes()
                    .route(r -> r.path("/price/**").uri("http://localhost:8002"))
                    .route(r -> r.path("/inventory/**").uri("http://localhost:8003"))
                    .build();
            }
        }


- you can match on different fields:
    - .path() => match on url path
    - .cookie() => match on cookie regex
    - .after() => match if datetime is after the date
    - .between() => match if datatime is between ranges
    - .predicate() => match on predicate


:ref:`Go Back <frameworks-java-spring-cloud-gateway-label>`.

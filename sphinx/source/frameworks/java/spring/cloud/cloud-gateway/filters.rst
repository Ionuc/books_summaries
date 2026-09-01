.. _frameworks-java-spring-cloud-gateway-filters-label:

Filters
=======
- are used to pre-process or post-process a request before / after sending to the target microservice
- predefined filters:
    - addRequestHeader:
        - will add request header
    - addRequestParam
        - will add a request paraameter
    - changeRequestURi
    - circuitBreaker
    - dedupeReponseHeader
    - modifyRequestBody
    - redirect
    

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
                    .route(r -> r.path("/price/**").filter(f-> f.addRequestHeader("key", "value")).uri("http://localhost:8002"))
                    .route(r -> r.path("/inventory/**").uri("http://localhost:8003"))
                    .build();
            }
        }

Custom Filter
-------------
- can be used to create cross-cutting concerns, like:
    - security
    - logging
    - metrics
- is applied only to routes configured
- steps:
    - create new component extending AbstractGatewayFilterFactory<>
        - override public GatewayFilter apply(Config config):
            - return a function with 2 params:
                - exchange => will provide the request
                - chaing => used to send back the response
            - you can change anything from the request: headers, body, path, etc ..
            - you can do changes before send the request to target microservice, or after receiving the response


    .. code-block:: python
        :linenos:

        @Component
        public class CustomGatewayFilter extends AbstractGatewayFilterFactory<CustomGatewatFilter.Config> {

            public CustomGatewayFilter() {
                super(Config.class);
            }

            public GatewayFilter apply(Config config) {
                return (Echange, chain) -> {
                    ServerHttpRequest = echange.getRequest();

                    if (!request.getHeaders().containsKey("Auhtorization")) {
                        return this.onError(echange, "No Authorization header found", HttpStatus.UNAUTHORIZED);
                    }

                    String authorizationHEader = request.getHeaders().get("Authorization").get(0);

                    if(!this.authorizationVAlid(authorizationHeader)) {
                        return this.onError(echange, "Invalid Authorizaion header", HttpStatus.UNAUTHORIZED);
                    }

                    ServerHttpRequest modifiedRequest = exhange.getRequest().mutate.path("/price/103").build();

                    // Pre-Filter
                    return chain.filter(echange.mutate.request(modifiedRequest).build());

                    // Post-Filter
                    /**
                    return chain.filter(echange).then(Mono.fromRunnable(() -> {
                        System.out.println("Post Filter");
                    }))
                    **/ 
                }
            }

            private Mono<Void> onErrorServerWebExchange(exchage, String err, HttpStatus httpStatus) {
                ServerHttpResponse = echange.getResponse();
                response.setStatus(httpStatus);
                return response.setComplete();
            }

            private boolean isAuthorizationValid(String header) {
                / code to check auth
                return true;
            }

            public static class Config {
                // config properties
            }
        }

    - use the custom filter in the SpringCloudConfig


    .. code-block:: python
        :linenos:

        @Configuration
        public class SpringCloudConfig {
            @Bean
            public RouteLocator buildRouteLocator(RouteLocatorBuilder builder, CustomGatewayFilter customGatewayFilter) {
                return builder.routes()
                    .route(r -> 
                        r.path("/price/**")
                        .filters(f-> customGatewayFilter.apply(new Config()))
                        .uri("http://localhost:8002"))
                    .route(r -> 
                        r.path("/inventory/**")
                        .filters(f-> f.addRequestHeader("key", "value"))
                        .uri("http://localhost:8003"))
                    .build();
            }
        }


GlobalFilter
------------
- is a filter which applies to all requests
- the custom implementation should implement GlobaFilter


    .. code-block:: python
        :linenos:

        @Component
        public class LoggingGlobalPreFilter implement GlobaFilter {

            @Override
            public Mono<Void> filter (ServerWebEchange echange, GatewayFilterChain chain) {
                System.out.println("Global Pre Filter executed");
                return chain.filter(exchange);
            }
        } 

:ref:`Go Back <frameworks-java-spring-cloud-gateway-label>`.

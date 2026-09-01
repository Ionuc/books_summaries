.. _frameworks-java-spring-cloud-resilience4j-general-label:

Spring Resilience4j
====================
- provides implementations for Circuit Breaker Pattern, Retry Pattern
- library:
    - groupId: io.github.resilience4j
    - artifactId: resilience4j-spring-boot2


Circuit Breaker
---------------



    .. code-block:: python
        :linenos:

        resiliece4j:
            circuitebreaker:
                instances:
                    myService:
                        slidingWindowsSize: 10
                        failureRateThreashold: 50
                        waiatDurationInOpenState: 30s
                        permittedNumberofCallsInHalfOpenState: 3
                        slidingWindowType: COUNT_BASED



    .. code-block:: python
        :linenos:

        @RestController
        public class MyController {
            @GetMapping("/api")
            @CircuiteBreaker(name="myService", fallbackMethod = "fallback")
            public String callService() {
                return "Success";
            }

            public String fallback(Exception e) {
                return "Fallback response: Service is temporarily unavailable";
            }
        }


Retry Pattern
-------------


    .. code-block:: python
        :linenos:

        resiliece4j.retry:
            instances:
                myRetry:
                    maxAttepmts: 5
                    waitDuration: 1000s
                    enableExponentialBackoff: true
                    exponentialBackoffMultiplier:2
                    maxWaitDuration: 100000ms


    .. code-block:: python
        :linenos:

        @Service
        public class MyService {
            @Retry(name="myRetry", fallbackMethod = "fallbackMethod")
            public String callExternalService() {
                return "Success";
            }

            public String fallbackMethod(Exception e) {
                return "Fallback response due to: " + e.getMessage();
            }
        }

- configure programatically


    .. code-block:: python
        :linenos:


    .. code-block:: python
        :linenos:

        @Service
        public class MyService {
            @Retry(name="myRetry", fallbackMethod = "fallbackMethod")
            public String callExternalService() {
                RetryConfig config = RetryConfig.custom()
                    .maxAttempts(5);
                    .waitDuration(Duration.ofMillis(1000))
                    .retryException(RuntimeException.class)
                    .build();

                Retry retry = Retry.of("myRetry", config);

                Supplier<String> supplier = Retry.decorateSupplier(retry, this::callExternalService);

                try {
                    suppluer.get();
                } catch (Exception e) {
                    return "Fallback response due to : " + e.getMessage();
                }
                return "Success";
            }

            public String callExternalService() {
                return "Success";
            }

        }


:ref:`Go Back <frameworks-java-spring-cloud-resilience4j-label>`.
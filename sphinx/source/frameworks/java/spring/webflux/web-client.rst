.. _frameworks-java-spring-webflux-web-client-label:

Web Client
==========

- is the alternative way of sending request to and endpoint in reative way
- webClient uses an asynchronous, non-blocking solution provided by the Spring Reactive framework.
- while RestTemplate uses the caller thread for each event (HTTP call), WebClient will create something like a “task” for each event.
    - :ref:`See Rest Template <frameworks-java-spring-rest-template-label>`.

- behind the scenes, the Reactive framework will queue those “tasks” and execute them only when the appropriate response is available
- the return type is Flux publisher


    .. code-block:: python
        :linenos:

        @GetMapping(value = "/tweets-non-blocking", 
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
        public Flux<Tweet> getTweetsNonBlocking() {
            log.info("Starting NON-BLOCKING Controller!");
            Flux<Tweet> tweetFlux = WebClient.create()
              .get()
              .uri(getSlowServiceUri())
              .retrieve()
              .bodyToFlux(Tweet.class);

            tweetFlux.subscribe(tweet -> log.info(tweet.toString()));
            log.info("Exiting NON-BLOCKING Controller!");
            return tweetFlux;
        }


Methods
-------
- get(), post(), etc:
   - to create a request with corresponding Request message type
- retrieve():
    - to fetch the response
- bodyValue(MyRequest):
    - to send a request with Body
- bodyToMono(<class>):
    - to convert the response to a Mono having one single type
- bodyToFlux(<class>):
    - to convert the response to a Flux with multiple object instances
- entity(<class>):
    - convert the response to a Mono<ResponseEntity<class>>>

Handling Success Response
-------------------------
- you can convert the response to a ResponseEntity:


    .. code-block:: python
        :linenos:

        public Mono<ResponseEntity<String>> fetchData() {
            return webClient.get()
                    .uri("/data")
                    .retrieve()
                    .toEntity(String.class);
        }


- in case you need the response, you can retrieve it, by bocking the current thread:
    - Flux.block()


Handling Error Response
-----------------------


    .. code-block:: python
        :linenos:

        public Mono<String> fetchDataSafely() {
            return webClient.get()
                    .uri("/data")
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, response ->
                        Mono.error(new RuntimeException("Client Error: " + response.statusCode())))
                    .onStatus(HttpStatus::is5xxServerError, response ->
                        Mono.error(new RuntimeException("Server Error: " + response.statusCode())))
                    .bodyToMono(String.class);
        }


Implementing Retries
--------------------
- Use .retryWhen() to retry failed requests


    .. code-block:: python
        :linenos:

        public Mono<String> fetchDataWithRetry() {
            return webClient.get()
                    .uri("/data")
                    .retrieve()
                    .bodyToMono(String.class)
                    .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(2))); // Retries 3 times
        }


DefaultWebClientBuilder
-----------------------
- in order to have full customization about the WebClient create, it can be used the DefaultWebClientBuilder
    - set base URL
    - set default Cookie
    - set default Header
    - override client Connector
    - etc


    .. code-block:: python
        :linenos:

        WebClient client = WebClient.builder()
          .baseUrl("http://localhost:8080")
          .defaultCookie("cookieKey", "cookieValue")
          .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
          .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
          .build();


Setting timeouts
----------------
- You can set timeouts for WebClient requests using exchangeStrategies:
    - Note that while we can call timeout on our client request as well, this is a signal timeout, not an HTTP connection, a read/write, or a response timeout; it’s a timeout for the Mono/Flux publisher.


    .. code-block:: python
        :linenos:

        HttpClient httpClient = HttpClient.create()
          .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
          .responseTimeout(Duration.ofMillis(5000))
          .doOnConnected(conn -> 
            conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
              .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

        WebClient client = WebClient.builder()
            .baseUrl("https://api.example.com")
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .build();


When to use
-----------
- best for reactive applications where non-blocking behavior is needed.
- ideal for handling multiple concurrent requests efficiently.

:ref:`Go Back <frameworks-java-spring-webflux-label>`.
.. _frameworks-java-spring-rest-template-label:

Rest Template
=============
- provides a traditional synchronous and blocking approach
- uses the Java Servlet API, which is based on the thread-per-request model:
    - this means that the thread will block until the web client receives the response
    - the application will create many threads, which will exhaust the thread pool or occupy all the available memory


    .. code-block:: python
        :linenos:

        @GetMapping("/tweets-blocking")
        public List<Tweet> getTweetsBlocking() {
            log.info("Starting BLOCKING Controller!");
            final String uri = getSlowServiceUri();

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<Tweet>> response = restTemplate.exchange(
              uri, HttpMethod.GET, null,
              new ParameterizedTypeReference<List<Tweet>>(){});

            List<Tweet> result = response.getBody();
            result.forEach(tweet -> log.info(tweet.toString()));
            log.info("Exiting BLOCKING Controller!");
            return result;
        }


Methods
-------
- getForObject:
    - send a GET request to the endpoint, wait for the response and convert it to the Model:

    .. code-block:: python
        :linenos:


        @Component
        public class ProductsRestClient {

          @Autowired
          private RestTemplate restTemplate;

          private static final String productsUrl = "https://api.product.com/name/watch";

          // Method to perform a GET request using getForObject and retrieve the response body
          public Products getForObject() {
            return restTemplate.getForObject(productsUrl, Products.class);
          }
        }


- getForEntity:
    - send a GET request to the endpoint, wait for the response and convert it to the Model:

    .. code-block:: python
        :linenos:


        @Component
        public class ProductsRestClient {

          @Autowired
          private RestTemplate restTemplate;

          private static final String productsUrl = "https://api.product.com/name/watch";

          // Method to perform a GET request using getForEntity and retrieve the response body
          public Products getForEntity() {
            ResponseEntity<Products> response = restTemplate.getForEntity(productsUrl, Products.class);
            return response.getBody();
          }

        }


- postForObject:
    - send a POST request to the endpoint, wait for the response and convert it to the model class
    - the recommended aproach to send data for an endpoint having RequestParams is by using UriComponentsBuilder:


   .. code-block:: python
        :linenos:


        @PostMapping("")
        public Map<String, Object> createProduct(@RequestBody Product product) {
            String resourceUrl = "httpL//localhost:8080/product/";
            HttpHeader headers = new HttpHeders();
            headers.set("Accept", MediaType.APPLICAITON_JSON_VALUE);
            HttpEntity<?> request = new HttpEntity<>(headers);

            UriComponentsBuilder builder - UriComponentsBuilder.fromURL(resourceUrl)
                .queryParam("id", product.getProductID())
                .queryParam("name", product.getName())
                .queryParam("price", product.getPrice());

            Map<String, Object> productResponse = restTemplate.postForObject(builder.toUriString(), request, Map.class);

            return productResponse;
        }


- exchange()
    - send a request to the endpoint
    - it needs:
        - Url endpoint
        - HttpMethod (GET, POST, PUT)
        - HttpEntity (headers or body)
        - class to convert


   .. code-block:: python
        :linenos:


        @PostMapping("")
        public Map<String, Object> createProduct(@RequestBody Product product) {
            String resourceUrl = "httpL//localhost:8080/product/";
            HttpHeader headers = new HttpHeders();
            headers.set("Accept", MediaType.APPLICAITON_JSON_VALUE);
            HttpEntity<?> request = new HttpEntity<>(headers);

            UriComponentsBuilder builder - UriComponentsBuilder.fromURL(resourceUrl)
                .queryParam("id", product.getProductID())
                .queryParam("name", product.getName())
                .queryParam("price", product.getPrice());

            Map<String, Object> productResponse = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, request, Map.class);

            return productResponse;
        }


-headForHeaders:
    - return the header values:


   .. code-block:: python
        :linenos:


        @Component
        public class ProductsRestClient {

          @Autowired
          private RestTemplate restTemplate;

          private static final String productsUrl = "https://api.product.com/name/watch";

          // Method to retrieve headers using headForHeaders and format them into a map
          public Map<String, String> getHeaders() {
            HttpHeaders httpHeaders = restTemplate.headForHeaders(productsUrl);

            Map<String, String> headerContent = new HashMap<>();
            httpHeaders.forEach((key, value) -> {
              headerContent.put(String.format("Header '%s'", key),
                String.join("|", value)); // Join header values with "|" separator
            });

            return headerContent;
          }
        }



:ref:`Go Back <frameworks-java-spring-rest-label>`.
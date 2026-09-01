.. _frameworks-java-spring-restclient-general-label:

RestClient
==========
- is an alternative for:
    - RestTemplate:
        - is the old way using blocking requests
    - WebFlux:
        - which is using reactive programming with Mono & Flux

- library artifact name : spring-boot-starter-restclient


Creating RestClient
-------------------
- it can be used the auto-configured builder using @Autowire annotation


    .. code-block:: python
        :linenos:

        @Autowired
        private RestClient.Builder restClientBuilder;

        public void test() {
            RestClient restClient = restClientBuilder.build();
        }


- it can be created manually with default settings:


    .. code-block:: python
        :linenos:

        RestClient restClient = RestClient.create();


- it can be created from a RestTemplate instance



    .. code-block:: python
        :linenos:

        RestTemplate oldRestTemplate;
        RestClient restClient = RestClient.create(oldRestTemplate);


Get Request
------------


    .. code-block:: python
        :linenos:

        String articlesAsString = restClient.get()
            .uri(uriBase + "/articles")
            .retrieve()
            .body(String.class);

        assertThat(articlesAsString).isEqualToIgnoringWhitespace("""
            [{"id":1,"title":"How to use RestClient"}]
          """);


Post Request
------------


    .. code-block:: python
        :linenos:

        Article article = new Article(1, "How to use RestClient");
        ResponseEntity<Void> response = restClient.post()
            .uri(uriBase + "/articles")
            .contentType(APPLICATION_JSON)
            .body(article)
            .retrieve()
            .toBodilessEntity();



Put Request
------------


    .. code-block:: python
        :linenos:

        Article updatedArticle = new Article(id, "How to use RestClient even better");
        restClient.put()
            .uri(uriBase + "/articles/" + id)
            .contentType(MediaType.APPLICATION_JSON)
            .body(updatedArticle)
            .retrieve()
            .toBodilessEntity();


Delete Request
--------------


    .. code-block:: python
        :linenos:

        restClient.delete()
            .uri(uriBase + "/articles/" + id)
            .retrieve()
            .toBodilessEntity();


Configure Api Versioning
------------------------
- When invoking external APIs that support multiple versions, we can use the ApiVersionInserter class to configure the API version in our RestClient instance
- ApiVersionInserter also supports inserting the version using HTTP header,request path, query parameter, or media type.


    .. code-block:: python
        :linenos:

        RestClient versionedClient = restClient
            .mutate()
            .defaultApiVersion("2")
            .apiVersionInserter(ApiVersionInserter.useHeader("API-Version"))
            .build();


Deserializing Response
----------------------
- Deserializing to a Non-Generic Class, converting the JSON response into a typed object


    .. code-block:: python
        :linenos:

        Article fetchedArticle = restClient.get()
            .uri(uriBase + "/articles/" + id)
            .retrieve()
            .body(Article.class);


- Deserializing to a Generic Class using ParameterizedTypeReference


    .. code-block:: python
        :linenos:

        List<Article> articles = restClient.get()
            .uri(uriBase + "/articles")
            .retrieve()
            .body(new ParameterizedTypeReference<>() {});

- Custom Converters:
    - This approach is particularly useful when we need to apply custom serialization or deserialization rules across all requests made by a specific client


    .. code-block:: python
        :linenos:

        JsonMapper jsonMapper = JsonMapper.builder()
            .findAndAddModules()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .build();

        RestClient customClient = restClient
            .mutate()
            .configureMessageConverters(converters -> converters
                .registerDefaults()
                .jsonMessageConverter(new JacksonJsonHttpMessageConverter(jsonMapper)))
            .build();



Parsing Response With Exchange
------------------------------
- The RestClient includes the exchange() method for handling more advanced situations by granting access to the underlying HTTP request and response
- example of throwing an exception based on status returned

    .. code-block:: python
        :linenos:

        List<Article> article = restClient.get()
            .uri(uriBase + "/articles")
            .exchange((request, response) -> {
                if (response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(204))) {
                    throw new ArticleNotFoundException();
                } else if (response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200))) {
                    return jsonMapper.readValue(response.getBody(), new TypeReference<>() {});
                } else {
                    throw new InvalidArticleResponseException();
                }
            });


Error Handling
--------------
- By default, when RestClient encounters a 4xx or 5xx status code in the HTTP response, it raises an exception that’s a subclass of RestClientException.
- We can override this behavior by implementing our own status handler using the onStatus() method


    .. code-block:: python
        :linenos:

        Article article = restClient.get()
            .uri(uriBase + "/articles/1234")
            .retrieve()
            .onStatus(status -> status.value() == 404, (request, response) -> {
                throw new ArticleNotFoundException(response);
            })
            .body(Article.class);

:ref:`Go Back <frameworks-java-spring-restclient-label>`.
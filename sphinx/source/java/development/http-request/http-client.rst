.. _java-development-http-request-http-client-label:

Http Client
===========
- relesed with Java 11
- is designed to be more modern, flexible and feature rich comparing to to legacy HTTP url connection

Handling response
-----------------
- multiple body handlers are defined in HttpResponse.BodyHandlers
- these can be used when sending request synchronously or asynchronously
    - ofString()
        - reads the body into a String
    - ofByteArray()
        - reads the body into a byte array
    - of File()
        - reads the body and copies the content to a file

Sending request
---------------
- send synchronously
    - default request method is GET
    - send() method is used 
    - is needed a BodyHandler to specify how the response body should be processed or handled when receiving and HTTP response

    .. code-block:: python
           :linenos:

        System.out.println("* Sending Get request synchronously: ");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://google.com"))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());


- send asynchronously
    - use sendAsync():
        - will return a CompletableFuture

    .. code-block:: python
           :linenos:

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://google.com"))
                .build();


        System.out.println("* Sending Get request asynchronously: ");
        CompletableFuture<HttpResponse<String>> future = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        future.thenAccept(response2 -> {
            System.out.println("Response Code: " + response2.statusCode());
            System.out.println("Response Body: " + response2.body());
        });

        future.join(); // Wait for the request to complete
        
        System.out.println();
        System.out.println("* Customizing Request");
        HttpRequest requestCustomized = HttpRequest.newBuilder()
                .uri(URI.create("https://example.com"))
                .header("User-Agent", "Java 11 HttpClient")
                .timeout(Duration.ofSeconds(10))
                .build();

Custom request
--------------
- custom headers can be added to the request

    .. code-block:: python
           :linenos:

        System.out.println("* Customizing Request");
        HttpRequest requestCustomized = HttpRequest.newBuilder()
                .uri(URI.create("https://example.com"))
                .header("User-Agent", "Java 11 HttpClient")
                .timeout(Duration.ofSeconds(10))
                .build();


Working with cookies
--------------------
- CookieManager can be used to store in-memory values
- it manages cookies and their associated policies for an HTTP client
- cookies are store in the implementation of CookieStore interface
    - in case null value is passed in the CookieManager, the default implementation will be used
- implementation of CookiePolicy is used to filter which cookies should be stored or not


    .. code-block:: python
           :linenos:

        System.out.println("* Cookie management demo: ");
        CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
        HttpClient httpClient2 = HttpClient.newBuilder()
                .cookieHandler(cookieManager)
                .build();

        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create("https://www.google.com"))
                .build();
        HttpResponse<String> response3 = httpClient2.send(request2, HttpResponse.BodyHandlers.ofString());
         // Print the cookies received
        cookieManager.getCookieStore().getCookies().forEach(cookie -> {
            System.out.println("Received Cookie: " + cookie);
        });


Handle redirect
---------------
- when creating the instance of HttpClient, it can be set the Redirect enum value using builder method followRedirects()


    .. code-block:: python
           :linenos:

        System.out.println("* Configure redirects: ");
        HttpClient httpClient3 = HttpClient.newBuilder()
                .followRedirects(Redirect.ALWAYS)
                .build();
        HttpResponse<String> response4 = httpClient3.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response Code: " + response4.statusCode());
        System.out.println("Response Body: " + response4.body());


:ref:`Go Back <java-development-http-request-label>`.
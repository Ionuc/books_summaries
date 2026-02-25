.. _rest-rest-params-label:

Rest Parameters
===============

- there are 3 types of parameters:
    - Path parameters
    - Query parameters
    - Request body parameters
    - Header

Path Parameters
---------------
- Path parameters are found within the path of the endpoint before the query string (?)
- the path parameters are set off using curly braces.
- path parameters are used to identify a specific resource or resources


    .. code-block:: python
           :linenos:

            /service/myresource/user/{user}/bicycles/{bicycleId}
            /cars/:id


Query Parameters
----------------
- These are the most common type of parameters.
- You can find them in the query string of the endpoint, after the ?.
- query parameters are used to sort/filter those resources


    .. code-block:: python
           :linenos:

            /cars?color=blue&brand=ferrari


Request Body Parameters
-----------------------
- You’ll see these most often in POST / PUT / PATCH requests, where values are sent in the request body.
- The request body parameters carry the actual payload or data being sent to the server, commonly used for transmitting data like JSON objects, form data, or file uploads


    .. code-block:: python
           :linenos:

            myparam1=123&myparam2=abc&myparam2=xyz


Header
------
- they represent the meta-data associated with the API request and response.
- Headers carry information for:
    - Request and Response Body
    - Request Authorization
    - Response Caching
    - Response Cookies
- Most of these headers are for management of connections between client, server and proxies and do not require explicit validation through testing.
- there are 2 types of headers:
    - request headers
    - response headers

- most common headers:
    - Authorization: Carries credentials containing the authentication information of the client for the resource being requested.
    - WWW-Authenticate: This is sent by the server if it needs a form of authentication before it can respond with the actual resource being requested. Often sent along with a response code of 401, which means ‘unauthorized’.
    - Accept-Charset: This is a header which is set with the request and tells the server about which character sets are acceptable by the client.
    - Content-Type: Indicates the media type (text/html or text/JSON) of the response sent to the client by the server, this will help the client in processing the response body correctly.
    - Cache-Control: This is the cache policy defined by the server for this response, a cached response can be stored by the client and re-used till the time defined by the Cache-Control header.

:ref:`Go Back <rest-label>`.
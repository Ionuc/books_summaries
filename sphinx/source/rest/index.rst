.. _rest-label:

Rest API
========
- A REST API is an application programming interface (API) that conforms to the design principles of the representational state transfer (REST) architectural style
- REST APIs provide a lightweight way to build web APIs and are commonly used to facilitate data exchange between applications, web services and databases, and to connect components in microservices architectures

- At the most basic level, an API is a mechanism that enables an application or service to access a resource within another application, service or database
- The application or service that accesses resources is the client, and the application or service that contains the resource is the server
- Some APIs, such as SOAP or XML-RPC, impose a strict framework on developers

How REST APIs work
------------------
- REST APIs communicate through HTTP requests to perform standard database functions like CRUD
- For example, a REST API would use a GET request to retrieve a record. A POST request creates a new record. A PUT request updates a record, and a DELETE request deletes one
- The state of a resource at any particular instant, or timestamp, is known as the resource representation
- This information can be delivered to a client in virtually any format including JavaScript Object Notation (JSON), HTML, XLT, Python, PHP or plain text
- Request headers and parameters are also important in REST API calls because they include important identifier information such as metadata, authorizations, uniform resource identifiers (URIs), caching, cookies and more
- Request headers and response headers, along with conventional HTTP status codes, are used within well-designed REST APIs

Best Practices
--------------
- The OpenAPI Specification (OAS) establishes an interface for describing an API in a way that allows any developer or application to discover it and fully understand its parameters and capabilities
- Use hashing algorithms for password security and HTTPS for secure data transmission. An authorization framework like OAuth 2.0can help limit the privileges of third-party applications.
- Using a timestamp in the HTTP header, an API can also reject any request that arrives after a certain time period. Parameter validation and JSON Web Tokens are other ways to ensure that only authorized clients can access the API

REST API vs SOAP API
--------------------

SOAP:
- has long been the most widely used protocol for interfacing different systems via the web
- specifications are maintained by the World Wide Web Consortium (W3C)
- It is often wrongly compared to REST. Indeed, SOAP is a protocol, while REST is an architecture style
- However, the two are not compatible because REST was created hoping to solve some problems associated with SOAP, which made it too inflexible a protocol.
- The major drawback of SOAP that led to the majority adoption of REST is the use of XML

- SOAP still has some advantages over REST in specific settings:
    -  It is compatible with all programming languages and many protocols such as HTTP, TCP, SMTP, JMS, or UDP
    - It also supports various extensions such as WS-Security, WS-Federation, WS-Coordination

.. toctree::
    :maxdepth: 2
    :caption: Contents:

    rest_principals.rst
    rest_methods.rst
    rest_best_practices.rst
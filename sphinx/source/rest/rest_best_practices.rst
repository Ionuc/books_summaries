.. _rest-rest-best_practices-label:

Rest Best Practices
===================

The OpenAPI Specification
-------------------------
- The OpenAPI Specification (OAS) establishes an interface for describing an API in a way that allows any developer or application to discover it and fully understand its parameters and capabilities

Resource Naming Conventions
---------------------------
- Designing intuitive and consistent URI structures is crucial for creating user-friendly and maintainable REST APIs
- Guidelines for Designing URI Structures:
    1. Use Nouns, Not Verbs: URIs should represent resources, so use nouns to name endpoints. For example, use /users instead of /getUsers.
    2. Hierarchical Structure: Organize URIs to reflect resource hierarchy. For example, use /users/{userId}/orders/{orderId} to indicate that orders belong to users.
    3. Plural Names for Collections: Use plural nouns for collections of resources. For example, use /users for a collection of user resources.
    4. Use Hyphens for Readability: Use hyphens to improve readability of multi-word URIs. For example, use /user-profiles instead of /userProfiles.
    5. Consistent Naming Conventions: Apply a consistent naming convention throughout your API. Stick to a format like snake_case or camelCase, and use it consistently.

- examples:
    - Good: /users, /users/{userId}, /users/{userId}/orders
    - Bad:  /getUsers, /Users/{id}, /users/{userId}/getOrders

Versioning Strategies
---------------------
- Versioning APIs is essential for managing changes and ensuring backward compatibility for clients
- common methods for versioning APIs:
    1. URL Versioning:
        - Example: /v1/users
        - Pros: Easy to understand and implement. Clear separation of versions.
        - Cons: Can lead to URI clutter if many versions are maintained.

    2. Header Versioning:
        - Example: GET /users with Accept: application/vnd.example.v1+json
        - Pros: Cleaner URLs. Versioning information is abstracted from the URI.
        - Cons: Less visible and harder to test with simple tools like cURL.

    3. Query Parameter Versioning:
        - Example: /users?version=1
        - Pros: Easy to implement and test.
        - Cons: Clutters query parameters and mixes versioning with other query options.

Handling Errors and Status Codes
--------------------------------
- Returning meaningful HTTP status codes and error messages is crucial for API usability and debugging
- Use Standard HTTP Status Codes:
    - 200 OK: Request succeeded.
    - 201 Created: Resource created successfully.
    - 400 Bad Request: Client-side input validation failed.
    - 401 Unauthorized: Authentication required.
    - 403 Forbidden: Client authenticated but does not have permission.
    - 404 Not Found: Resource not found.
    - 500 Internal Server Error: Server encountered an unexpected condition.

- Standardizing Error Responses:


    .. code-block:: python
           :linenos:

            {
                "error": {
                    "code": 400,
                    "message": "Invalid input data",
                    "details": [
                        {
                        "field": "email",
                        "issue": "Email format is invalid"
                        }
                    ]
                }
            }


Security Considerations
-----------------------
- Implementing robust security measures is vital to protect your REST APIs from various vulnerabilities

1. Authentication and Authorization:
    - OAuth 2.0: A widely-used authorization framework that allows third-party services to exchange user credentials without exposing user passwords.
    - JWT (JSON Web Tokens): A compact, URL-safe means of representing claims to be transferred between two parties.

2. Protecting Against Common Vulnerabilities:
    - SQL Injection: Use prepared statements and parameterized queries to prevent attackers from injecting SQL commands.
    - Cross-Site Scripting (XSS): Sanitize user input to prevent malicious scripts from being executed in the context of your web application.
    - DDoS Attacks: Implement rate limiting, throttling, and use API gateways to protect your API from being overwhelmed by excessive requests

:ref:`Go Back <rest-label>`.
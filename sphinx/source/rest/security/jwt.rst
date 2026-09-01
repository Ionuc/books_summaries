.. _rest-security-jwt-label:

JWT
===
- stands for "JSON Web Token"
- is a compact and secure method for transmitting information between parties as a JSON object
- It is commonly used for authentication and authorization in web applications, allowing users to access protected resources without repeatedly providing credentials
- properties:
    - Stateless Authentication: Stores user information in a token, reducing the need for server-side session storage.
    - Secure Data Exchange: Uses digital signatures to verify that the token has not been altered.
    - Compact Format: Consists of three parts—Header, Payload, and Signature—encoded into a single string.
    - Widely Supported: Works across different programming languages and platforms.


Structure
---------
- it is composed of 3 parts:
    - Header
    - Payload
    - Signature

    .. image:: ../../images/rest/security/jwt/jwt-structure.png
       :align: center


- Header
    - The header contains metadata about the token, including the signing algorithm and token type
    - alg: Algorithm used for signing (e.g., HS256, RS256).
    - typ: Token type, always "JWT".


    .. code-block:: python
           :linenos:

            {
                "alg": "HS256",
                "typ": "JWT"
            }


- Payload
    - The payload contains the information about the user also known as a claim and some additional information including the timestamp at which it was issued and the expiry time of the token.


    .. code-block:: python
           :linenos:


            {
                "userId": 123,
                "role": "admin",
                "exp": 1672531199
            }


    - Common claim types:
        - iss (Issuer): Identifies who issued the token.
        - sub (Subject): Represents the user or entity the token is about.
        - aud (Audience): Specifies the intended recipient.
        - exp (Expiration): Defines when the token expires.
        - iat (Issued At): Timestamp when the token was created.
        - nbf (Not Before): Specifies when the token becomes valid.


- Signature
    - The signature ensures token integrity and is generated using the header, payload, and a secret key.


    .. code-block:: python
           :linenos:

            HMACSHA256(
                base64UrlEncode(header) + "." + base64UrlEncode(payload),
                secret
            )


Flow
----
- steps:
    - Login Request: The user logs in through the client application (e.g., web or mobile app) by sending their credentials (username & password) to the server.
    - Server Generates JWT: If the credentials are correct, the server generates a JWT token using a secret key.
    - Returns JWT: The server sends the JWT back to the client application.
    - Further Requests with JWT: For any subsequent requests, the client sends the JWT along with the request. The server verifies the JWT before granting access to protected resources.


    .. image:: ../../images/rest/security/jwt/authentication-flow.png
       :align: center



Best Practices
--------------
- Follow these best practices to improve the security of JWT-based authentication.
    - Use HTTPS: Encrypt communication between the client and server.
    - Store Tokens Securely: Prefer HttpOnly cookies over Local Storage for sensitive tokens.
    - Set Short Expiration Times: Keep access tokens short-lived (for example, 15–60 minutes).
    - Use Refresh Tokens: Generate new access tokens without requiring users to log in again.
    - Keep Secret Keys Secure: Store secrets in environment variables and never hardcode them.
    - Verify JWT Signature: Always validate the token's signature before trusting its contents.
    - Validate JWT Claims: Verify claims such as exp, iss, aud, and nbf before granting access.
    - Avoid Sensitive Data in Payload: Store only the information required for authentication and authorization.
    - Handle Invalid Tokens Properly: Return appropriate HTTP status codes without exposing sensitive error details.

Common JWT Errors and Issues
----------------------------
- The following are some common errors you may encounter while implementing JWT authentication:
    - Invalid Signature: The signing and verification keys do not match.
    - Token Expired: The JWT has passed its expiration time (exp).
    - Malformed Token: The JWT is incomplete, corrupted, or has an invalid format.
    - Missing Authorization Header: The request does not include the Authorization header.
    - Invalid Bearer Token: The Bearer prefix is missing or the token format is invalid.
    - JWT Rejected: The server failed to validate the token due to expiration, an invalid signature, or invalid claims.
    - Insufficient Scope: The token lacks the required permissions for the requested action.
    - JWT Decode Failed: The token cannot be decoded because it is malformed or improperly encoded.

:ref:`Go Back <rest-security-label>`.
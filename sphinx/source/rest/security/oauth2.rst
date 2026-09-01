.. _rest-security-oauth2-label:

OAuth2
======
- stands for "Open Authorization"
- OAuth2.0 is an Open industry-standard authorization protocol that allows a third party to gain limited access to another HTTP service
-  OAuth 2.0 provides consented access and restricts actions of what the client app can perform on resources on behalf of the user, without ever sharing the user's credentials
- As such, it is designed primarily as a means of granting access to a set of resources, for example, remote APIs or user data.

- OAuth 2.0 is an authorization protocol and NOT an authentication protocol
- OAuth 2.0 uses Access Tokens.

- OAuth is about delegated access:
    - Delegation is a process in which an owner authorizes a service provider to perform certain tasks on the owner’s behalf.
- OAuth allows granular access levels:
    - Rather than entrusting our entire protected data to a third party, we would prefer to share just the necessary data with them
    -  we need a trusted intermediary that would grant limited access(known as scope) to the editor without revealing the user's credentials once the user has granted permission

- Concepts:
    - Actors
    - Scopes and Consent
    - Tokens
    - Flows

Actors
------
- bellow are the possible actors:
    - Resouces
    - Resource Owner:
        - The user or system that owns the protected resources and can grant access to them.
    - Resource Sever:
        - A server that protects the user’s resources and receives access requests from the Client
        - It accepts and validates an Access Token from the Client and returns the appropriate resources to it
    - Client:
        - is a third-party application that wants to access the data
        - To access resources, the Client must hold the appropriate Access Token
    - Authorization Server:
        - OAuth's main engine that creates access tokens
        - This server receives requests from the Client for Access Tokens
        - issues them upon successful authentication and consent by the Resource Owner.
        - exposes 2 endpoints:
            - the Authorization endpoint:
                - handles the interactive authentication and consent of the user,
            - the Token endpoint:
                - which is involved in a machine to machine interaction.


Scope and Consent
-----------------
- The scopes define the specific actions that apps can perform on behalf of the user

Token
-----
- A token is a piece of data containing just enough information to be able to verify a user's identity or authorize them to perform a certain action
- Anyone who has the access token can use it to make API requests
- OAuth access tokens can be created without actually including information about the user to whom they were issued
- Like a movie ticket, an OAuth access token is valid for a certain period and then expires
- Access tokens are credentials used to access protected resources
- Each token represents the scope and duration of access granted by the resource owner and enforced by the authorization server
- The format, structure, and method of utilizing access tokens can be different depending on the resource server's security needs

-  A Refresh token is a string issued to the client by the authorization server and is used to obtain a new access token when the current access token becomes invalid:
    - They do not refresh an existing access token, they simply request a new one
    - The expiration time for refresh tokens tends to be much longer than for access tokens

- The OAuth 2 Authorization server may not directly return an Access Token after the Resource Owner has authorized access. 
    - Instead, and for better security, an Authorization Code may be returned, which is then exchanged for an Access Token.


How is working
--------------
- the general flow is:
    - 1. The Client requests authorization (authorization request) from the Authorization server, supplying the client id and secret to as identification:
        - it also provides the scopes and an endpoint URI (redirect URI) to send the Access Token or the Authorization Code to
    - 2. The Authorization server authenticates the Client and verifies that the requested scopes are permitted.
    - 3. The Resource owner interacts with the Authorization server to grant access.
    - 4. The Authorization server redirects back to the Client with either an Authorization Code or Access Token, depending on the grant type
    - 5. With the Access Token, the Client requests access to the resource from the Resource server


Grant Types
-----------
- grants are the set of steps a Client has to perform to get resource access authorization

- grants:
    - Authorization Code grant:
        - The Authorization server returns a single-use Authorization Code to the Client, which is then exchanged for an Access Token
        - This is the best option for traditional web apps where the exchange can securely happen on the server side
        - the client secret cannot be stored securely, and so authentication, during the exchange, is limited to the use of client id alone
        -  A better alternative is the Authorization Code with PKCE grant
    - Implicit Grant:
        - A simplified flow where the Access Token is returned directly to the Client
        - he authorization server may return the Access Token as a parameter in the callback URI or as a response to a form post
    - Authorization Code Grant with Proof Key for Code Exchange (PKCE):
        - This authorization flow is similar to the Authorization Code grant, but with additional steps that make it more secure for mobile/native apps and SPAs
    - Resource Owner Credentials Grant Type:
        - This grant requires the Client first to acquire the resource owner’s credentials, which are passed to the Authorization server
        - It is, therefore, limited to Clients that are completely trusted
        - It has the advantage that no redirect to the Authorization server is involved, so it is applicable in the use cases where a redirect is infeasible
    - Client Credentials Grant Type:
        - Used for non-interactive applications e.g., automated processes, microservices, etc
        - In this case, the application is authenticated per se by using its client id and secret
    - Device Authorization Flow:
        - A grant that enables use by apps on input-constrained devices, such as smart TVs.
    - Refresh Token Grant:
        - The flow that involves the exchange of a Refresh Token for a new Access Token.


:ref:`Go Back <rest-security-label>`.
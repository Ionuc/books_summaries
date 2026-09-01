.. _rest-http-message-label:

Http Message
============


Http Request Message
--------------------
- contains 3 parts:
    - request line
        - has the actual HTTP command (or method), like GET, POST, DELETE
    - header variables:
        - request medatada
    - message body:
        - contents of message, or payload

Http Response Message
---------------------
- contains 3 parts:
    - response line
        - contains server protocol and status code (200, 400, etc)
    - header variables:
        - response medatada
        - the actual information about the data, like:
            - content type
            - the size / lenght of data
    - message body:
        - contents of message, or payload

HTTP Response Status code
-------------------------
- range [100-199]:
    - informational
- range [200-299]:
    - Success
- range [300-399]:
    - Redirection
- range [400-499]:
    - Client error
    - 401 => Autehntication Required
    - 404 => File not Found
- range [500-599]:
    - Server error
    - 500 => Internal Server Error

MIME Content Type
-----------------
- stands for Multipurpose Internal Mail-Extension
- the message format is described by MIME content type
- basic syntax: type/sub-type
- this information is send to the client in order to e processed accordingly
- example:
    - text/html => the client will render it based on HTML tags
    - text/plain => the client will just print the text in the browser without any processing
    - application/json => send for REST clients
    - application/xml => send for REST clients

:ref:`Go Back <rest-label>`.
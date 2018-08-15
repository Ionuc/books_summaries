.. _web-sockets-label:

Web Sockets
===========
    - allow for full duplex communication between browser and server.
    - what does means is that as well as the browser sending the server data, the server can also send the browser data
    - with the traditional HTTP, the browser sends a request to the server to grab the data, the server cannot send data directly to the browser
    - are supported in modern servers and in modern browsers
    - the way it works:
        - the client and server perform a WebSocket handshake
        - after that, they can send data two ways
        - one side or other can decide to end the communication, the other side will receive the corresponding event to close the connection

Java API for WebSockets
-----------------------
    - java provides an API for this included in JEE
    - API for creating a WebSocket server:
        - programmatic endpoints
        - annotated endpoints
    - allows to send bot test and binary data
    - it also allows to use hava types in the server, and convert those java types into JSON and send it to the client, and convert it back to the original type
    - supports URI Templates

Path parameters
---------------
    - a path parameter is simply a name that we append to the end of the endpoint using braces to specify that this is a variable name
    - each client can connect to an URL using different parameters, and that parameter value is passed through to each method that the server endpoint exposes, such as
      onOpen(), or the onMessage() method

:ref:`Go Back <sockets-label>`.
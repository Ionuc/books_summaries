.. _java-development-socket-nio-label:

Nio Sockets
===========
- are implemented with JAva 13
- Socket (java.net.Socket and java.net.ServerSocket) APIs were reimplemented using NioSocketImpl interface instead of PlainSocketImpl interface
    - in order to use the old implementation


    .. code-block:: python
           :linenos:

            We can start the JVM with the system property -Djdk.net.usePlainSocketImpl set as true 



:ref:`Go Back <java-development-function-label>`.
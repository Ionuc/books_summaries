.. _java13_socket_api:

Reimplement The Legacy Socket API
=================================

    - Socket (java.net.Socket and java.net.ServerSocket) APIs were reimplemented using NioSocketImpl interface instead of PlainSocketImpl interface
    - in order to use the old implementation


    .. code-block:: python
           :linenos:

            We can start the JVM with the system property -Djdk.net.usePlainSocketImpl set as true 




:ref:`Go Back <java13-developer_features-label>`.
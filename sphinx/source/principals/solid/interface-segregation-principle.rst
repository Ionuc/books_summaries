.. _interface-segregation-principle-label:

Interface Segregation Principle
===============================
    - clients should not be forced to depend on methods they do not use
    - we should thing as interfaces are not defined by the concrete class that implements the interface, but it's defined by the client that consumes the interface
    - since a client defines an interface, it should contain only relevant information
    - favor Role Interfaces over Header Interfaces:
        - a Header interface is extracted from a concrete clas and has lots of members(the name came from the old-fashion Header files)
        - a Role interface is an interface that defines very few members

:ref:`Go Back <solid-principals-label>`.
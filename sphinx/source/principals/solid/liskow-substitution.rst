.. _liskow-substitution-label:

Liskow Substituion Principle
============================
    - tals about polymorphism
    - the idea is that substypes must be substitutable for their base types
    - objects in a program should be replaceble with instances of their subtypes without altering the correctness of the program
    - other definition would be : Consume any implementation without changing the correctness of the system:
        - you can think about the correctness of the system as all the correct behavior that a system might exhibit
        - is specific to application
        - there is a single universal rule : The system should not crash
        - example: if you have a client that talks to version A of an interface and that does not couse the entire system to crash, if that client changes
          to talk to an implementation of B of the same interface and that causes the system to crash, then you have changed the correctnesss of the system

    - how to violate LSP:
        - when the code throws NotSupportedException
            => solution: split the interface into smaller one, so the class throwing NotSupportedException to not be force to override that method
        - if you have a lot of downcasts
        - extracted interfaces
        - is often violated by attempts to remove features

:ref:`Go Back <principals-solid-label>`.
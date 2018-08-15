.. _python-inheritance-subtype-label:

Inheritance and Subtypes
========================
    - base class initializer will only be called automatically if subclass initializer is undefined
    - providing the __init__ method to subclass will not call automatically the __init__ of the base class
    - you can use build-in methods :
        - isintance()
            - determines if an object is of a specified type
            - use this method for runtime type checking
        - issubclass():
            - determines if one type is a subclass of another

Multiple inheritance
--------------------
    - you can have multiple inheritance in Python
    - you can define a class with more than one base class
    - subclasses inherit methods of all bases
    - without conflict, names resolved in the obvious way
    - Method Resolution Order (MRO) determines name lookup in all cases
    - if a class has multiple base classes and defines no initializer, then only the initializer of the first base class is automatically called
    - __base__ is a tuple defininig the base classes

Method Resolution Order
-----------------------
    - is the ordering that determines which implementation to use when a method is invoked
    - is called MRO
    - methods may be defined in multiple places
    - the MRO of a class determines the order in which the inheritance graph is searched to find the correct implementation of the method
    - this information is stored inside field __mro__ or method mro()
    - in Python, when you call a method on an object:
        - Python looks at the MRO for that object's type
        - for each entry in the MRO starting the front, Python cehcks if that class has the requested method
        - if it founds the class with a matching method, it uses that method and the search stops
    - Python used C3 algorithm for calculating MRO:
        - ensure subclasses come before base classes
        - ensure base class order from class definition is preserved
        - first two qualities are preserved no matter where you start in the inheritance graph
    - not all inheritance declarations are allowed

:ref:`Go Back <python-label>`.

.. _oop-general-label:

General OOP
===========

Immutability
------------
- an immutable class is a simple class where the state and behavior for all instances cannot be modified
- all the information contained in each instance is provided when it is created and is fixed for the lifetime
- to make a class immutable:
    - don't provide any methods that modify the object's state ( known as mutators )
    - ensure that the class cannot be extended
    - make all fields final
    - make all fields private
	- ensure exclusive access to any mutable components:
        - if the class has any fields that refer to mutable objects, caller shouldn't obtain references to the objects
        - make defensive copies in constructors, accessors and readObject method
- drawback:
    - it is required a separate object for each distinct value

:ref:`Go Back <oop-label>`.
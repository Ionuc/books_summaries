.. _java-development-class-object-cloning-objects:

Cloning Objects
===============
- Object.clone() method can be used to clone an object
    - it is protected and using implies overriding the method with public access modifier
    - CloneNotSupportedException is thrown if class is not implementing the market interface Clonable and clone() method is called

Type
----
- there are 2 types of cloning:
    - shallow cloning:
        - create a new instance only for the parent object, but refering the same references for child object
    - deep cloning
        - create a new instance for all parent and all its child, and nested child objects


:ref:`Go Back <java-development-class-object-label>`.
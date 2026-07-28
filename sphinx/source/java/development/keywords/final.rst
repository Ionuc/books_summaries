.. _java-development-keywords-final-label:

Final keyword
==============

Immutability
------------
    - the class is immutable means once the instance of that class is created, the state inside cannot be changed from outside
    - immutability is useful in concurrent environment
    - in Java it can be implemented using final keyword

Final on attribute
------------------
    - if the attribute is primitive, the value cannot be changed
    - if the attribute is object, the reference cannot be changed

Final on method
---------------
    - the method cannot be overridden

Final on class
--------------
    - the corresponding class cannot be extended


Effectively final variables
---------------------------
- a variable is considered effectively final it its values doesn't change after it is assigned
- form Java 8, compiler can recognize effectivaly final variables and you don't have to marke them as final

:ref:`Go Back <java-development-label>`.
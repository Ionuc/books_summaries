.. _java-development-data-type-primitive-label:

Primitive types
===============
- primitive types are:
    - integers:
        - byte
        - short
        - int
        - long
    - floating-point numbers:
        - float
        - double
    - boolean:
        - boolean
    - characters:
        - char


    .. image:: ../../../images/java/development/data-types/primitive/primitive.png
        :align: center


Integers
--------
- default value for integers is 0
- default type for the result after doing arithmetic operation on byte or short or int is int
- operation implying long will result in long type


   .. code-block:: python
        :linenos:

        byte b1 = 1;
        byte b2 = 1;
        byte b3 = b1 + b2 // compilation error as the expected type is int



Floating-point Numbers
----------------------
- default type for teh result afte rdoing arithmetic operation on floating-pint number is double

Converting types
----------------
- you can do un box between a type with lower byte allocation to a type with higher byte allocation
- overflows:
    - situation when manually cast one type to a smaller type.
    - for integers, it will roll over to the minimum value and begins counting up from there
    - from double to long, even if the types contains the same amount of bytes, information can be lost because the double and floating point numbers are stored different from the long values. It will imply a translation from 64 bit floating point value to 64 bit integer representation
- widening conversion: changes a smaller data type to a larger data type (e.g., int to double or byte to short).

Autoboxing vs unboxing
----------
- Autoboxing is automatic conversion between primitive to their wrapper types


   .. code-block:: python
        :linenos:

        Integer i = 1;


- unboxing is the automatic conversion between wrapper to the primitive type


   .. code-block:: python
        :linenos:

        int i = Integer.valueOf("1");

Default Values
--------------
- default values are:
    - for int => 0
    - for double => 0.0
    - for boolean => false

:ref:`Go Back <java-development-data-type-label>`.
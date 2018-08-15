.. _types-primitives-label:

Primitives
==========

Int
---
    - arbitrary precision integer
    - unlimited precision signed integer
    - we can construct integer from int() which can convert from other numeric types, or string
    - roading is always towards zero
    - has and arbitrary magnitude limited only by practical constraints of available memory and the time required to manipulate large numbers
    - on other langauges, int is a fixed size storing only 16-, 32-, or 64-bits of precision

Float
-----
    - 64-bit floating point numbers
    - are double precision floating-point number
    - 53 bits of binary precision
    - 15 to 16 bits of decimal precision
    - is composed of :
        - 1 bit for sign
        - 11 bits for exponent
        - 52 for fraction, or mantisa
    - because mantisa is composed of 53 bits, we can't represent every integer above 2 to the 53
    - you can use float() constructor

None
----
    - to be used with capital letter : "None"
    - the null object
    - represents the absent of the value

Bool
----
    - boolean logical values
    - represents the logical state
    - False & True
    - bool(integer|float):
        - return false only for 0
        - return true for the other values, like 42, -1
    - bool(collection)
        - false for []
        - true for list with elements
    - bool(string)
        - false for ""
        - true for any other string

:ref:`Go Back <python-types-label>`.
    

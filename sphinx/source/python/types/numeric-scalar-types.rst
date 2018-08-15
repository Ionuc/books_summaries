.. _types-numeric-scalar-types-label:

Numeric and Scalar Types
========================
Decimal
-------
    - the Decimal type from the decimal module is a fast correctly rounded number type for performing arithmetic in base 10
    - is stil a floating type
    - if you use constructor with the float value, you will have same wrong results as using directly with the floats
    - you should use Decimal with the string parameter
    - precision is preserved when the Decimal was constructed from the parameter
    - you can use : Decimal('Infinity'), Decimal('-Infinity'), Decimal('NaN')

Fraction
--------
    - used to represent rational numbers which consist of quotient of two integers
    - denominator should be different than 0
    - you can create a Fraction from int, float (it might bring wrong values), Decimal, String

Complex
-------
    - used to represent complex numbers
    - a "J" suffix is placed onto a number where "J" represents the imaginary square root of -1
    - python uses the electrical engineering notation for imaginary numbers
    - complex construction provides a string which can have parentheses but must not contain spaces
    - in order to use extra helpers on complex, use cmath (and not math) library

Datetime
--------
    - represent time related quantities
    - the types are:
        - date:
            - Gregorian Calendar
            - field: year, month, day
        - time:
            - field: hour, minute, second, microsecond
        - datetime
        - timedelta
            - difference between two times
            - field: days, seconds, microseconds
    - all these objects are immutable

:ref:`Go Back <python-types-label>`.
    

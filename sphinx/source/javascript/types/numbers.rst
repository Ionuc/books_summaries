.. _javascript-numbers-label:

Number
======
    - The single number data type
    - all numbers in JavaScript are stored in 64-bit format, also known as "double precision"
    - Decimal fractions are not exact
    - toFixed(n) - returns the number to n decimal places
    - there are only 3 numeral systems with support : hexadecimal, binary and octal
    - for other numeral system you can use method parseInt()

toString(base)
--------------
    - num.toString(base) returns a string representation of num in the numeral system with the given base

    .. code-block:: python
        :linenos:

        let num = 255;

        alert( num.toString(16) );  // ff
        alert( num.toString(2) );   // 11111111

    - calling .toString() directly on value implies to use double dots. If it is used only once, JavaScript will try think
      as it is the decimal part of the number

    .. code-block:: python
        :linenos:

        alert( 123456..toString(36) ); // 2n9c

Rounding
--------
    - there are several built-in functions for rouding:
        - Math.floor:
            - rounds down: 3.1 becomes 3. -1.1 becomes -2
        - Math.ceil:
            -rounds up : 3.1 becomes 4, -1.1 becomes -1
        - Math.round:
            - rounds to the nearest integer
            - 3.1 becomes 3, 3.6 becomes 4
            - -1.1 becomes -1
        - Math.trunc:
            - removes anything after the decimal point without rounding
            - 3.1 becomes 3, -1.1 becomes -1

Imprecise calculations
----------------------
    - internally, a number is represented in 64-bit format:
        - 52 to store the digit
        - 11 to store the position of the dicmal point (they are zero for integer)
        - 1 for the sign
    - if the number is too long, it will give infinity

    .. code-block:: python
        :linenos:

        alert( 1e500 ); // Infinity

    - the lost of precision can be often lost:

    .. code-block:: python
        :linenos:

        alert( 0.1 + 0.2 == 0.3 ); // false
        alert( 0.1 + 0.2 ); // 0.30000000000000004

    - this is happening because:
        - a number is stored in memory in its bynary representation
        - a fraction is stored as unending fractions in their binary form
        - is like storing 1/3 as 0.3333(3)
        - so division by power 10 is guarenteed to work well in the decimal system, but dvision by 3 not
        - for the same reason, in the binary system, the division by power 2 is guaranteed to work, but 1/10 becomes endless binary
          fraction

        - the numeric firmat IEEE-754 solves this by rounding to the nerest possible number
        - the most reliable method is to round the result with the help of a method toFixed(n)

Tests: isFinite and isNaN
-------------------------
    - there are 2 special numeric values:
        - Infinity ( and -Infinity) -> is a special numeric value that is greater(less) than anything
        - NaN -> represents an error
    - isNaN(value)
        - converts the argument to a nnumber and test it for being NaN

        .. code-block:: python
            :linenos:

            alert( isNaN(NaN) ); // true
            alert( isNaN("str") ); // true

        - the NaN is unique, meaninig is not equal to itself

        .. code-block:: python
            :linenos:

            alert( NaN === NaN ); // false

    - isFinite(value):
        - converts the argument to a number and returns true if it is a regular umber, not Infinity, -Infinity, NaN

parseInt and parseFloat
-----------------------
    - numeric convertion using a plus "+" or "Number()" is strict. If a value is not exactly a number, it fails

    .. code-block:: python
        :linenos:

        alert( +"100px" ); // NaN

    - but there are cases, like in CSS, where you want to read a number from a string until you can't

    .. code-block:: python
        :linenos:

        alert( parseInt('100px') ); // 100
        alert( parseFloat('12.5em') ); // 12.5

        alert( parseInt('12.3') ); // 12, only the integer part is returned
        alert( parseFloat('12.3.4') ); // 12.3, the second point stops the reading

:ref:`Go Back <javascript-types-label>`.
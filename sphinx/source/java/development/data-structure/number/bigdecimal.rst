.. _java-development-number-bigdecimal-label:

BigDecimal
==========
- provides operations for arithmetic, scale manipulation and rounding
- if no rouding mode is specified and exact result cannot be represented, then exception java.lang.ArithmeticException is thrown
- rounding errors are expected for double and floats because there are infinit number of double and floats, but finit number of bits:
    - the number you get is the closest number possible that can be represented by double in floating point representation


    .. code-block:: python
           :linenos:

            double d = 3.1
            double d2 = 1.21
            System.out.println(d-d2) // => will print 1.8900000000000001


- solution: use BigDecimal:



    .. code-block:: python
           :linenos:

            BigDecima bd3 = BigDecimal.valueOf("3.1").setScale(2);
            BigDecima bd4 = BigDecimal.valueOf("1.21").setScale(2);
            System.out.println(bd3.substract(bd4)) // => will print 1.89

:ref:`Go Back <java-development-number-label>`.
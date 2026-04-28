.. _java-development-number-math-label:

Math class
==========
- contains a set of basic operations such as optaining the absolut value, highest and lowest values of 2 values, rounding values, etc
- contains 2 constans:
    - E:
        - the double values which is closer tahn any other to the base of natural logarithms
    - PI: 
        - the PI number 
        - the ration between the circumference of a circle to its diameter


- some operations with floating point number can produce not a number result (NaN)


    .. code-block:: python
           :linenos:

            System.out.println(Math.sqrt(-1)) // NaN
            System.out.println(0 / 0.0)) // NaN
            System.out.println(0 / 0.0) + 5) // NaN


- there is also Infinit number :



    .. code-block:: python
           :linenos:

            System.out.println(5 / 0.0)) // Infinity
            System.out.println(-5 / 0.0) + 5) // -Infinity


- in order to have a scale set for Math.round(), you can multiple by 10^N and then divide by 10^N, where N is the scale



    .. code-block:: python
           :linenos:

            System.out.println(Math.round(20.0/3.0)) // 7
            System.out.println(Math.round(20.0 * 100.0/3.0) / 100.0) // 6.67 


- Math,random():
    - generate number between [0,1]
    - to generate a number between [0, 100], you can:



    .. code-block:: python
           :linenos:

            System.out.println((int)Math.random() * 100) // generate a number between [0,100]
            System.out.println( 100 + (int)Math.random() * 100) // generate a number between [100,200]


:ref:`Go Back <java-development-number-label>`.
.. _java-development-operators-expression-label:

Expressions
===========
- expression:
    - is a construct made up of variables, operands and method invocations
    - example : "b + b"
- statements:
    - are roughly equivalent to senteces in natural languages
    - forms a complete unite of execution
    - example: "int intExpressionType = b + b;"


Expression Types
----------------
- aritmetic operators on byte, short, int will result in int type
- arithmetic operators from integers implying a long value will result in long type
- arithemtic operators between integers and floating-point number will result in floating point number
- arithmetic operators between any integer or floating-point number and a string will result in String value:
    - will concatenet the number to the string


   .. code-block:: python
        :linenos:

        byte b1 = 1;
        byte b2 = 1;
        int b3 = b1 + b2;

        float f1 = 1;
        float f2 = 1;
        float f3 = f1 + f2;
        float f4 = b1 + f2;

        double d1 = 1;
        double d2 = 1;
        double d3 = d1+ f1;

        System.out.prinln(10 / 3); // 3
        System.out.prinln(10.0 / 3); // 3.333333333334

        System.out.prinln("Hello " + 1); // "Hello 1"
        System.out.prinln("Hello " + null); // "Hello null"


:ref:`Go Back <java-development-operators-label>`.
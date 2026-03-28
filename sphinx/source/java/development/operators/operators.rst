.. _java-development-operators-operators-label:

Operators
=========
- an operator is a symbol that performs a specific kind of operation on 1, 2 or 3 operands and produce a result
- it can be categories on 2 criteria:
    1. number of operands:
        - unary     => it takes 1 operands
        - bynary    => it takes 2 operands
        - ternary   => it takes 3 operands
    2. type of operation they perform:
        - arithmetic operator
        - relational operator
        - logical operator
        - bitwise opertor

Arithmetic unary operators
--------------------------
- they are:
    - "+"
        - indicates positiv value
    - "-"
        - negates an expression value
    - "++"
        - incrementor operator
        - increments by one
        - has 2 forms:
            - prefix:
                - value is incremented and only after that value will be used
                - example: ++i
            - postfix:
                - value will be used and only thet will be incremented
                - example: i++

    .. code-block:: python
        :linenos:

        int i = +10;
        int i2 = ++i
        int i3 = i++

        System.out.println(i);  // => will print 10
        System.out.println(i2); // => will print 11 and i will have value 11
        System.out.println(i3); // => will print 11 and i will have value 12


    - "--"
        - decrementor opertor
        - decrements by one
        - has 2 forms:
            - prefix:
                - value is incremented and only after that value will be used
                - example: ++i
            - postfix:
                - value will be used and only thet will be incremented
                - example: i++

   .. code-block:: python
        :linenos:

        int i = +10;
        int i2 = --i
        int i3 = i--

        System.out.println(i);  // => will print 10
        System.out.println(i2); // => will print 9 and i will have value 9
        System.out.println(i3); // => will print 9 and i will have value 8


Arithmetic bynary operators
---------------------------
- they are:
    - "+"
        - adds left operand with the right operand
        - used by String, it will concatenate those 2 Strings
    - "-"
        - substrac right hand operand from left hand operand
    - "/":
        - divides left hand operand by right hand operand
    - "%":
        - divides left hand operand by right hand operand and return the reminder 
    - "*":
        - multiples values left operand with right operand

Assignment operators
--------------------
- they are:
    - "="
        - assign rigth operand to the left operand
    - "+="
        - add right operands to the left operands and assign the result to the left operands
    - "-="
    - "*="
    - "/="
    - "%="

Relational operators
--------------------
- they are:
    - "=="
    - "!="
    - "<"
    - ">"
    - "<="
    - ">="

- compare values on either side of them an decides the relation amoung them
- the result is alwasy a boolean value

Logical Operators
-----------------
- they work only with boolean operands
- they are:
    - "&"
        - logical and
        - return true if both operands are true
        - will evaluate both operands
    - "&&"
        - short circuit and
        - JVM will not evaluate at all the second operand in case the first one is already false
    - "|"
        - logical or
        - returns true in case one of operands is true
        - will evaluate both operands
    - "||"
        - short circut or
        - JVM will not evaluate at all the second operand in case the first one is already true
    - "!"
        - logical unary not
        - will return false if operand is true, and false in case operand is true
    - "^"
        - logical XOR (exclusive or)
        - will return:
            - true in case only one operand is true
            - false in case operands are having the same value: both true or both false

Bitwise operators
-----------------
- are binary operators
- are working with numbers in bit representation (0 & 1)
- these operators are:
    - "&"
        - binary AND operator
        - copies the bit to the result if bit exists in both operands (both operands are having value 1)


   .. code-block:: python
        :linenos:

        System.out.println(4 & 5);  // => will print 4
        /**
                1 0 0
                & & &
                1 0 1
                -----
                1 0 0 = 4
        */


    - "|"
        - binary OR operator
        - copies the bit to the result if bit exists in at least one operands


   .. code-block:: python
        :linenos:

        System.out.println(4 | 5);  // => will print 5
        /**
                1 0 0
                | | |
                1 0 1
                -----
                1 0 1 = 5
        */


    - "^"
        - binary XOR operator
        - copies the bit to the result if bit is set in only one operand, but not both


   .. code-block:: python
        :linenos:

        System.out.println(4 ^ 5);  // => will print 1
        /**
                1 0 0
                ^ ^ ^
                1 0 1
                -----
                0 0 1 = 1
        */

    - "~"
        - binary Complement operator
        - is unary and have the effect of flipping bits


   .. code-block:: python
        :linenos:

        System.out.println(~1);  // => will print 1111111111111111111111111111110
        

    - ">>"
        - binary Right Shift Operator
        - the left operand values  is moved right by the number of bits specified by the right operand
        - right shif number will divide number by 2


   .. code-block:: python
        :linenos:

        System.out.println("5 = " + 0b101);
        System.out.println("5 >> 1 = " + (0b101 >> 1)); // will print 2 because 2 = 0b01
        

    - ">>>"
        - binary Shift Right Zero Operator
        - the left operand values  is moved right by the number of bits specified by the right operand and shifted values are filled up with zeros
        - will behave different with negative values comparing to ">>"


   .. code-block:: python
        :linenos:

        int negativeByteValue = 0b11111111111111111111111110000000; // = -128
        System.out.println("-128 = " + negativeByteValue);              // => -128 = -128
        System.out.println("-128 >>>1 = " + (negativeByteValue >>> 1)); // => -128 >>>1 = 2147483584
        System.out.println("-128 >>1 = " + (negativeByteValue >> 1));   // => -128 >>1 = -64


    - "<<"
        - binary Left Shit Operator
        - the left operand values  is moved left by the number of bits specified by the right operand
        - left shift number will multiply the value by 2



   .. code-block:: python
        :linenos:

        System.out.println("5 = " + 0b101);              
        System.out.println("5 << 1 = " + (0b101 >>> 1)); // => 5 << 1 = 10 (becasue 10 = 0b1010)


Ternary Operators
-----------------
- this operator works with 3 operands
- (condition) ? true expression1 : false expression2
- is the equivalent with if else statement

Operator Associativity
----------------------
- Operator associativity in Java determines the order of evaluation when multiple operators of the same precedence appear in an expression.
- Java supports two types of associativity:
    1. Left-to-Right Associativity:
        - When multiple operators of the same precedence appear in an expression, they are evaluated from left to right
        - For example, in the expression a + b - c, addition and subtraction have the same precedence and are left-associative, so the expression is evaluated as (a + b) - c.
        - operators:
            - Arithmetic operators: +, -, *, /, %
            - Relational operators: >, <, >=, <=
            - Logical AND/OR: &&, ||
            - Bitwise operators: &, |, ^, <<, >>

    2. Right-to-Left Associativity:
        - Right-to-Left Associativity means that operators are evaluated from right to left when they have the same precedence
        - Operators:
            - Assignment operators: =, +=, -=, etc.
            - Unary operators: ++, --, !, ~

Operator precedends
-------------------
- In Java, operator precedence specifies the order in which operations are performed within an expression
- When an expression contains multiple operators, those with higher precedence are evaluated before those with lower precedence
- for example : 10+20*30
    - The expression contains two operators, + (addition) and * (multiplication)
    - According to operator precedence, multiplication (*) has higher precedence than addition (+), so multiplication is checked first.
    - After evaluating multiplication, the addition operator is then evaluated to give the final result



    .. image:: ../../../images/java/development/operators/operators/operator_precedence.png
        :align: center





:ref:`Go Back <java-development-operators-label>`.
.. _javascript-types-label:

Types
=====

String
------
    - Is a primitive type representing an ordered set of characters

    .. code-block:: python
       :linenos:

       Var string1 = “The quick brown fox’s jump”;
       Var string2 = ‘“The quick brown fox”’;

    - String escape sequences:
        - Common escape sequences begin with \
        - New line \n
        - String literal delimiter \” or \’
        - Unicode symbols \u[code]

Number
------
    - The single number data type
    - Decimal fractions are not exact
    - toFixed(n) - returns the number to n decimal places

Array
-----
    - One of the 2 build-in data structure (besides Object)
    - Is an indexed collection
    - Can store anything
    - You can use push() & pop() methods to behave like a stack
    - You can sort elements. Default behavior is alphabetically. You can provide a
      function to the sort method

Date
----
    - No literal syntax
    - The month parameter is zero based: January is 0
    - Datejs library is bringing more helper methods

    .. code-block:: python
       :linenos:

       Var birthday = new Date(2019, 10, 26);

JSON
----
    - JavaScript Object Notation
    - Alternative to xml
    - Increasingly used in AJAX web applications
    - To parse JSON, you can use json2.js library:
        - parse()
        - stringify()

Null
----
    - One of the Javascript primitive types
    - Represents the absence of a value
    - Evaluates to false in boolean expressions. The same with the empty string and zero value

    .. code-block:: python
       :linenos:

       var mtvalue = null;
       if (!null){
           write("null evaluates to false");
       }
       var hasAValue = 1;
       if (hasAValue){
         write(hasAValue + " has a value")
       }
       Output
       null evaluates to false
       1 has a value

Undefined
---------
    - Also a JavaScript primitive type
    - Represents an unknown value, which is a variable or property which is not assigned
    - Is returned when a non-existing object property is called
    - Evaluates to false in boolean expressions

    .. code-block:: python
       :linenos:

       var notAssigned;
       write(notAssigned)
       if(!notAssigned){
         write("undefined evaluates to false");
       }
       var person = {
         name: "Frank"
       };
       write("non-existent object property: "+ person.age);
       write(typeof person)
       Output:
       undefined
       undefined evaluates to false
       non-existent object property: undefined
       Objec

:ref:`Go Back <javascript-label>`.
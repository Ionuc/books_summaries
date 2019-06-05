.. _javascript-general-label:

General
=======

Equality
--------
    - Objects are only equal to themself
    - Primitives are equals if the values match
    - Two sets of equality operators (== and ===)
        - The different is type coercion
        - == attempt to coerce the 2 operands into the same type before comparing
        - Is very bad thing to use == or != but you should use === or !== which is not
          doing type coercion because it might have the same value, but different type


    .. code-block:: python
       :linenos:

       // objecst are equal to themself
       var joe = {name: "Joeseph"};
       write("joe equals joe : " + (joe === joe));

       // objects are not equal to other objects with the same values
       var joe2 = {name: "Joeseph"};
       write("joe equals joe2: " + (joe === joe2))

       //pritives types are equal of their values match
       write(" apple == apple: "+ ("apple" == "apple"))
       write(" apple === apple: "+ ("apple" === "apple"))

       write(" apple == cat: "+ ("apple" == "cat"))
       write(" apple === cat: "+ ("apple" === "cat"))

       //inequality 
       write ("1 != 2: " + (1 != 2))
       write ("1 !== 2: " + (1 !== 2))

       // the ==operator
       write('1 == "1": '+ (1 =="1"))
       write('1 === "1": '+ (1 ==="1"))

       write('"" == 0: ' + ("" == 0))
       write('"" === 0: ' + ("" === 0))

    .. code-block:: python
       :linenos:

       Output:
       joe equals joe : true
       joe equals joe2: false
       apple == apple: true
       apple === apple: true
       apple == cat: false
       apple === cat: false
       1 != 2: true
       1 !== 2: true
       1 == "1": true
       1 === "1": false
       "" == 0: true
       "" === 0: false

Comments
--------

    .. code-block:: python
       :linenos:

    - // single comment line


    .. code-block:: python
       :linenos:

       /* multiple
       Comments
       Lines */

    - alert(“Hello world”) // comments can be appended to the end of line

Variables
---------
    - Are declared with var keyword
    - Don’t have a type until a value is assigned and at that point the type is inferred
    - If keyword var is omitted, then the scope of the variable is global

    .. code-block:: python
        :linenos:

        var streetNumber = 49;
        var streetName = "Brunswick";
        write(typeof streetNumber + " " + streetNumber);
        write(typeof streetName + " " + streetName);

        function add(first, second) {
            a = first;
            return a + second;
        }

        write (" 1 + 2 = " + add(1,2));
        write ( " a: "+ a);
        write ("54 + 18 = " + add(54, 18))
        write(" a: " + a)

        if (!window.a){
            write("a is undefined");
        }

The old "var"
-------------
    - it behaves similar to "let", it declares a variable

    .. code-block:: python
        :linenos:

        function sayHi() {
          var phrase = "Hello"; // local variable, "var" instead of "let"

          alert(phrase); // Hello
        }

        sayHi();

        alert(phrase); // Error, phrase is not defined

    - here are some differences:
        - var does not have block scope:
            - "var" variables are either function wide or global
            - if a code block is inside a function, then "var" variables becomes a function-level variables

        .. code-block:: python
            :linenos:

            if (true) {
              var test = true; // use "var" instead of "let"
            }

            alert(test); // true, the variable lives after if


        .. code-block:: python
            :linenos:

            for (var i = 0; i < 10; i++) {
              // ...
            }

            alert(i); // 10, "i" is visible after loop, it's a global variable

    - "var" are processed at the function start:
        - "var" variables are defined from the beginning of the function, no matter where the definition is
        - this can be haotic
        - this code below:

        .. code-block:: python
            :linenos:

            function sayHi() {
                phrase = "Hello";
                alert(phrase);
                var phrase;
            }

        is technically the same as:

        .. code-block:: python
            :linenos:

            function sayHi() {
                var phrase;
                phrase = "Hello";
                alert(phrase);
            }
        and even with this code, where block from if will not be executed:

        .. code-block:: python
            :linenos:

            function sayHi() {
                phrase = "Hello"; // (*)
                if (false) {
                    var phrase;
                }
                alert(phrase);
            }

:ref:`Go Back <javascript-label>`.
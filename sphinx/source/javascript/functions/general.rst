.. _javascript-functions-general-label:

Functions
=========
    - function are of type object, they are callable "action objects"
    - Functions can be invoked
    - There are multiple ways to declare a function:
        - Standard declaration:
            - function square(x){ return x*x;}
            - square(7) => 49
        - Named and assigned function
            - Var squareFunc = function square(x) {return x*x;}
            - The function it will be called by the variable name and not by the function name
            - squareFunc(7) => 49
        - Anonymous and assigned function
            - Var square = function (x) {return x*x’}
            - It debug mode there will be no name on the stack trace
        - Anonymous and immediately invoked:
            - (function(x){return x*x;})(numberToSquare);
    - It does not allow the function overloading. The last function will remain

    .. code-block:: python
       :linenos:

       Function add(num, num2, num3) {
           Return num + num2 + num3;
       }
       Function add(num, num2) {
           Return num + num2;
       }
       write (add(6,7)); // 13
       write(add(6,7,8); // 13

Function Overriding
-------------------
    - Functions cannot be overloaded
    - Provide parameter flexibility, passing too many or too few will not cause an error:
        - extra parameters are ignored, missing parameters are initialized with undefined
    - Object parameters are passed by reference and primitive parameters are passed by value

Properties
----------
    - function objects contain a few usable properties:
        - "name" property:
            - a function name is accessible as the "name" property
            - if a name is not specified, it is figured out from the context

            .. code-block:: python
               :linenos:

                function sayHi1() {
                  alert("Hi");
                }
                alert(sayHi1.name); // // sayHi1

                let sayHi2 = function() {
                  alert("Hi");
                }
                alert(sayHi2.name); // sayHi2 (works!)

                function f(sayHi3 = function() {}) {
                  alert(sayHi3.name); // sayHi3 (works!)
                }
                f();

        - "length" property:
            - returns the number of function parameters:

            .. code-block:: python
               :linenos:

                function f1(a) {}
                function f2(a, b) {}
                function many(a, b, ...more) {}

                alert(f1.length); // 1
                alert(f2.length); // 2
                alert(many.length); // 2

        - custom properties:
            - we can add properties of our own

            .. code-block:: python
               :linenos:

                function sayHi() {
                    alert("Hi");
                    // let's count how many times we run
                    sayHi.counter++;
                }
                sayHi.counter = 0; // initial value
                sayHi(); // Hi
                sayHi(); // Hi

                alert( `Called ${sayHi.counter} times` ); // Called 2 times


            - these properties can be access from the Lexical Environment where the function is attached

            .. code-block:: python
               :linenos:

                function makeCounter() {
                    function counter() {
                        return counter.count++;
                    };
                    counter.count = 0;
                    return counter;
                }

                let counter = makeCounter();

                counter.count = 10;
                alert( counter() ); // 10

Named Function Expression
-------------------------
    - Named Function Expression (or NFE), is a term for Function Expression that have a name

    .. code-block:: python
       :linenos:

        // Ordinary Function Expression
        let sayHi = function(who) {
          alert(`Hello, ${who}`);
        };

        // Named Function Expression
        let sayHi = function func(who) {
          alert(`Hello, ${who}`);
        };

    - there are 2 special things about the name "func":
        - it allows the function to referece itself internally
        - it is not visible outside of the function

        .. code-block:: python
           :linenos:

            let sayHi = function func(who) {
                if (who) {
                    alert(`Hello, ${who}`);
                } else {
                    func("Guest"); // use func to re-call itself
                }
            };
            sayHi(); // Hello, Guest

            // But this won't work:
            func(); // Error, func is not defined (not visible outside of the function)

:ref:`Go Back <javascript-functions-label>`.
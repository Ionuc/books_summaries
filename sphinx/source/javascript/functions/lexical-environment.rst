.. _javascript-lexical-environment-label:

Lexical environment
===================
    - in JavaScript, every running function, code block and the script as a whole have an internal (hidden) object called
      the Lexical Environment

    - the Lexical Environment object consist of 2 parts:
        - Environment record - in object that stores all local variables as its properties (and some other information like "this")
        - A reference to the outer lexical environment, the one associated with the outer code

Variables
---------
    - so a variable is just a property of the special internal object "Environment Record". To get or change a variables means
      "to get or change a property of that object"
    - the property is set when the line es executed

    .. image:: ../../images/javascript/lexical-environment/variable.png
        :align: center

    - from the picture above it can be see that when the execution is started, there is created an empty Environment Record which
      has no outer Lexical Environment
    - chaging variable implies to change the property value

Function declaration
--------------------
    - unlike let variables, they are fully initialized when the execution reaches them, but earlier, when a Lexical Environment
      is created.
    - for top-level functions, it means the moment when the script is started
    - that is why we can call a function declaration before it is defined

    .. image:: ../../images/javascript/lexical-environment/function.png
        :align: center

Inner and outer Lexical Environment
-----------------------------------
    - when a function is run, a new function Lexical Environment is created automatically
    - this Lexical Environment is used to store local variables and parameters of the call

    .. code-block:: python
        :linenos:

        let phraze = "Hello";
        function say(name){
            alert(`$(phrase), $(name)`);
        }
        say("John");

    - for the code above, when the method is called, 2 Lexical Environment will be :
        - the inner one (for function call for say())
            - it has a single variable "name", the function argument
            - it has a reference to the outer one.
        - the outer one (global):
            - it has the phrase and function itself.

    .. image:: ../../images/javascript/lexical-environment/inner-function-1.png
        :align: center

    - when the code wants to access a variable, the inner Lexical Environment is searched first, then the outer and os on, until 
      the global one
    - wihtout "use strict", an assignment to an undefined variable created a new global variable (for backwards compatbility)
    - in the example above, it will be :

    .. image:: ../../images/javascript/lexical-environment/inner-function-2.png
        :align: center

    - because of this, a function gets outer variables as they are now,it used the most recent values

    .. code-block:: python
        :linenos:

        let name = "John";
        function sayHi(name){
            alert('Hi, ' + name) ;
        }
        name = "Peter";
        sayHi(); // Pete

    - a new Lexical Environment is created for each call of a function, even if it is about the same function
    - we cannot access this Lexical Environment

Nested Functions
----------------
    - all functions "on birth" receive a hidden property [[Environment]] with a reference to the Lexical Environment of their creation
    - in other words, a function is "imprinted" with a refenrece to the Lexical Environment where it was born. [[Environmen]] is 
      the hidden function property that has that reference.

    .. image:: ../../images/javascript/lexical-environment/nested-1.png
        :align: center

The new Function
----------------
    - usually, a function remembers where it was born in the special property [[Environment]], by referencing the Lexical 
      Environment from where it's created
    - but when a function is created using new Function, its [[Environment]] reference the global one, not the curent Lexical 
      Environment

    .. code-block:: python
        :linenos:

        function getFunc() {
            let value = "test";
            let func = new Function('alert(value)');
            return func;
        }

        getFunc()(); // error: value is not defined

    - compared with the normal one:

    .. code-block:: python
        :linenos:

        function getFunc() {
            let value = "test";
            let func = function() { alert(value); };
            return func;
        }

        getFunc()(); // "test", from the Lexical Environment of getFunc

Code blocks and loops, IIFE
----------------------------
    - a Lexical Environment exists for any code block
    - it can be created when an if is found:

        .. image:: ../../images/javascript/lexical-environment/code-block-if.png
            :align: center

        - when the execution gets into the if, the new "if-only" Lexical Environment is created.
        - all variables and functions inside this Lexical Environment cannot be seen outside
    - it can be created when a for loop is found:
        - for a loop, each iteration has its onw Lexical Environment
    - it can be created for code blocks:
        - we can also use a "bare" code block "{...}" to isolate variable into a "local scope"
        - for example, in web broswers all scripts (except with type="module") share some global area. If we create a global 
          variable in one script, it will be available for others. It can also appears name conflict

        .. code-block:: python
            :linenos:

            {
                // do some job with local variables that should not be seen outside
                let message = "Hello";
                alert(message); // Hello
            }

            alert(message); // Error: message is not defined

    - it can be created for IIFE (immediately-invoked function expressions):
        - in the past, there was no block-level lexical environment
        - IIFE looks like: 

        .. code-block:: python
            :linenos:

            (function() {
                let message = "Hello";
                alert(message); // Hello
            })();

        - other ways of creating IIFE:

        .. code-block:: python
            :linenos:

            // Ways to create IIFE

            (function() {
                alert("Parentheses around the function");
            })();

            (function() {
                alert("Parentheses around the whole thing");
            }());

            !function() {
                alert("Bitwise NOT operator starts the expression");
            }();

            +function() {
                alert("Unary plus starts the expression");
            }();

Garbage collection
------------------
    - a Lexical Environment object dies when it becomes unreachable
    - ususally, a Lexical Environment is cleaned up and deleted after the functon run
    - but if there is a nested function that is still reachable after the end of the called function, then its [[Environment]]
      reference keeps the outer lexical environment alive as well

    .. code-block:: python
        :linenos:

        function f() {
            let value = 123;
            function g() { alert(value); }
            return g;
        }

        let g = f(); // g is reachable, and keeps the outer lexical environment in memory

    - in the example above, if the function f() is called multiple times, it will keep all the lexical environment objects for 
      resulting functions
    - in the code above, the lexical environment will be cleaned up

    .. code-block:: python
        :linenos:

        function f() {
            let value = 123;
            function g() { alert(value); }
            return g;
        }

        let g = f(); // while g is alive
        // there corresponding Lexical Environment lives

        g = null; // ...and now the memory is cleaned up

Real-life optimizations
-----------------------
    - in theory, while a fuction is alive, all outer variables are also retain
    - in practice, JavaScript engine try to optimize that. They analyze variable usage nad if it's easy to see that an outer
      variable is not used, it is removed
    - an important side effect in V8 (Chrome, Opera) is that such variables will become unavailable in debugging

    .. code-block:: python
        :linenos:

        function f() {
            let value = Math.random();

            function g() {
                debugger; // in console: type alert( value ); No such variable!
            }
            return g;
        }

        let g = f();
        g();

    - this can lead to funny debug problems:

    .. code-block:: python
        :linenos:

        let value = "Surprise!";

        function f() {
            let value = "the closest value";
            function g() {
                debugger; // in console: type alert( value ); Surprise!
            }
            return g;
        }

        let g = f();
        g();

:ref:`Go Back <javascript-label>`.
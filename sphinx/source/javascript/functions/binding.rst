.. _javascript-functions-binding-label:

Function Binding
================
    - when using setTimeout() method with object methods or passing object methods along, there is a known problem of "losing this"

Loosing this
------------
    - once a method is passed somewhere separately from the object, then "this" is lost

    .. code-block:: python
        :linenos:

       let user = {
            firstName: "John",
            sayHi() {
                alert(`Hello, ${this.firstName}!`);
            }
        };

        setTimeout(user.sayHi, 1000); // Hello, undefined!

    - to solve this problem ther are multiple solutions

Solution 1: a wrapper
---------------------
    - use a wrapping function

    .. code-block:: python
        :linenos:

        let user = {
            firstName: "John",
            sayHi() {
                alert(`Hello, ${this.firstName}!`);
            }
        };

        setTimeout(function() {
            user.sayHi(); // Hello, John!
        }, 1000);

    - the problem with this solution is that if user is changed right after the setTimeou(), then the changes will be used
      instead of the original method

Solution 2: bind
----------------
    - functions provide a built-in method "bind" that allows to fix this
    - the basic syntax is :

    .. code-block:: python
        :linenos:

        // more complex syntax will be little later
        let boundFunc = func.bind(context);

    - the result of func.bind(contex) is a special function-lie object, that is callable as function and transparently passes
      the call to func setting "this=contex"

    .. code-block:: python
        :linenos:

        let user = {
            firstName: "John"
        };
        function func(phrase) {
            alert(phrase + ', ' + this.firstName);
        }
        // bind this to user
        let funcUser = func.bind(user);

        funcUser("Hello"); // Hello, John (argument "Hello" is passed, and this=user)

    .. code-block:: python
        :linenos:

        let user = {
            firstName: "John",
            say(phrase) {
                alert(`${phrase}, ${this.firstName}!`);
            }
        };

        let say = user.say.bind(user);

        say("Hello"); // Hello, John ("Hello" argument is passed to say)
        say("Bye"); // Bye, John ("Bye" is passed to say)

    - if we want to bind all methods to a context, there is a "bindAll()" built-in method
:ref:`Go Back <javascript-functions-label>`.
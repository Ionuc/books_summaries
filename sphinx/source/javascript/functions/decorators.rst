.. _javascript-functions-decorators-label:

Decorators
==========
    - is a function that takes anotehr function and alter its bahvior, without changing the function itself
    - the benefits of using them are:
        - makes reusable components
        - do not add complexity to the original function
        - we can pipeline multiple decorators

    .. code-block:: python
        :linenos:

       function slow(x) {
            // there can be a heavy CPU-intensive job here
            alert(`Called with ${x}`);
            return x;
        }

        function cachingDecorator(func) {
            let cache = new Map();

            return function(x) {
                if (cache.has(x)) { // if the result is in the map
                    return cache.get(x); // return it
                }
                let result = func(x); // otherwise call func
                cache.set(x, result); // and cache (remember) the result
                return result;
            };
        }

        slow = cachingDecorator(slow);

        alert( slow(1) ); // slow(1) is cached
        alert( "Again: " + slow(1) ); // the same

        alert( slow(2) ); // slow(2) is cached
        alert( "Again: " + slow(2) ); // the same as the previous line

Using "func.call" for the context
---------------------------------
    - in case it is used "this" (the context) in the function itself, then calling the function itself will make the context
      to be lost
    - this can be solved by calling the function with syntax:

    .. code-block:: python
        :linenos:

        func.call(context, arg1, arg2, ...)

    .. code-block:: python
        :linenos:

        function sayHi() {
          alert(this.name);
        }

        let user = { name: "John" };
        let admin = { name: "Admin" };

        // use call to pass different objects as "this"
        sayHi.call( user ); // this = John
        sayHi.call( admin ); // this = Admin

    .. code-block:: python
        :linenos:

        let worker = {
            someMethod() {
                return 1;
            },

            slow(x) {
                alert("Called with " + x);
                return x * this.someMethod(); // (*)
            }
        };

        function cachingDecorator(func) {
            let cache = new Map();
            return function(x) {
                if (cache.has(x)) {
                    return cache.get(x);
                }
                let result = func.call(this, x); // "this" is passed correctly now
                cache.set(x, result);
                return result;
            };
        }

        worker.slow = cachingDecorator(worker.slow); // now make it caching

        alert( worker.slow(2) ); // works
        alert( worker.slow(2) ); // works, doesn't call the original (cached)

Going multi-argument with "func.apply"
--------------------------------------
    - in order to pass multiple arguments to a function, together with the context, it can be used the build-in function "apply":

    .. code-block:: python
        :linenos:

        func.apply(context, args)
        func(1, 2, 3);
        func.call(context, 1, 2, 3)
        func.apply(context, [1, 2, 3])


    .. code-block:: python
        :linenos:

        function say(time, phrase) {
          alert(`[${time}] ${this.name}: ${phrase}`);
        }
        let user = { name: "John" };
        let messageData = ['10:00', 'Hello']; // become time and phrase

        // user becomes this, messageData is passed as a list of arguments (time, phrase)
        say.apply(user, messageData); // [10:00] John: Hello (this=user)

Borrowing a method
------------------
    - sometimes it is needed to use method from a specific object to be used on another object which "looks like" the 
      original object
    - for example, an "array like" object will not contain join() method, like the arguments inside a function:

    .. code-block:: python
        :linenos:

        function hash() {
            alert( arguments.join() ); // Error: arguments.join is not a function
        }

        hash(1, 2);

    - the solution would be to "borrow" the join() method from a real array method and to execute the method with the
      desired arguments

    .. code-block:: python
        :linenos:

        function hash() {
            alert( [].join.call(arguments) ); // 1,2
        }

        hash(1, 2);

:ref:`Go Back <javascript-functions-label>`.
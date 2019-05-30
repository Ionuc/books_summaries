.. _javascript-global-label:

Global object
=============
    - the global object provides variables and functions that are available anywhere
    - in browser it is named "window", for Node.js it is "global"

    .. code-block:: python
        :linenos:

        alert("Hello");

        // the same as
        window.alert("Hello");

Browser: the "window" object
----------------------------
    - the window object is a little bit messed up:
        - it provides the browser window functionality, besides playing the role of a global object

        .. code-block:: python
            :linenos:

            alert(window.innerHeight); // shows the browser window height

            window.open('http://google.com'); // opens a new browser window

        - top-level "var" variables and functions declarations automatically become properties of window. It is not the case with "let/const"

        .. code-block:: python
            :linenos:

            var x = 5;
            alert(window.x); // 5 (var x becomes a property of window)
            window.x = 0;
            alert(x); // 0, variable modified

            let x = 5;
            alert(window.x); // undefined ("let" doesn't create a window property)

        - all scripts share the same global scope, so the variables declared in one scripts becomes visible in another ones

        .. code-block:: python
            :linenos:

            <script>
              var a = 1;
              let b = 2;
            </script>

            <script>
              alert(a); // 1
              alert(b); // 2
            </script>

        - the value of "this" in the global scope is window

        .. code-block:: python
            :linenos:

            alert(this); // window

    - as you can see, it is not good that scripts can see other's variables. This can be solved by using type="module" for script, which will make the script as a separate "module", with its own top-level scope (not infering from window)

    .. code-block:: python
        :linenos:

        <script type="module">
          let x = 5;
        </script>

        <script type="module">
          alert(window.x); // undefined
          alert(x); // Error: undeclared variable
        </script>

Valid uses of the global object
-------------------------------
    - using global variables is generally discouraged. But if we use it, we may put it into global window, like :

    .. code-block:: python
        :linenos:

        // explicitly assign it to `window`
        window.currentUser = {
            name: "John",
            age: 30
        };

        // then, elsewhere, in another script
        alert(window.currentUser.name); // John

    - we can test the global object for support of modern languages features:

    .. code-block:: python
        :linenos:

        if (!window.Promise) {
            alert("Your browser is really old!");
        }

    - we can create "polyfills": add functions that are not supported by the environment, but exists in moder browsers:

    .. code-block:: python
        :linenos:

        if (!window.Promise) {
          window.Promise = ... // custom implementation of the modern language feature
        }

:ref:`Go Back <javascript-label>`.
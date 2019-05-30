.. _javascript-functions-new-syntax-label:

The new Function syntax
=======================
    - there is one way to create a function, rarely used
    - the declaration is the above :

    .. code-block:: python
        :linenos:

        let func = new Function ([arg1[, arg2[, ...argN]],] functionBody)

    - the first part consist of arguments, then the body
    - all the parameters ar of type string

    .. code-block:: python
        :linenos:

        let sayHi = new Function('alert("Hello")');
        sayHi(); // Hello

    - it is used to transform a string to a function

:ref:`Go Back <javascript-functions-label>`.
.. _javascript-methods-of-primitives-label:

Methods of Primitives
---------------------
    - a primitive :
        - is a value of a primitive type
        - there are 6 primitive types: stirng, number, boolean, symbol, null, undefined
    - an object:
        - is capable of sotring multiple values as properties
        - can be created with {}
        - there are other kinds of objects in JavaScript like : functions
        - objects can store functions
        - objects are havier than primitives -> they require additional resources to support the internal mechinery

A primitive as an object
------------------------
    - there are special object wrapper which provides the extra functionality and then destroyed
    - they are : String, Number, Boolean and Symbol
    - there is no wrapped object for null and undefined primitives

    .. code-block:: python
       :linenos:

       let str = "Hello";

        alert( str.toUpperCase() ); // HELLO

    - what is happing in code above is :
        - the string str is a primitive. So in moment of accessing its property, a special object is created that knows the value of
          the string nad has useful methods like: toUpperCase()

        - that methods runs and returns a new string
        - the special object is destroyed, leaving the primitive str alone

    - the JavaScript it is possible to create new instances of these wrapped objects, but it is highly unrecommended

    .. code-block:: python
       :linenos:

        let zero = new Number(0);

        if (zero) { // zero is true, because it's an object
            alert( "zero is truthy?!?" );
        }

:ref:`Go Back <javascript-types-label>`.

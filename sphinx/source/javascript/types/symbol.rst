.. _javascript-numbers-label:

Symbol
======
    - by specification, object property keys may be either to string type, or of symbol type
    - object's key cannot be numbers, or boolean
    - symbol values represents a unique identifier

    .. code-block:: python
        :linenos:

        // id is a new symbol
        let id1 = Symbol();

        // id is a symbol with the description "id"
        let id2 = Symbol("id");

    - symbols are guaranteed to be unique. Even if we create many symbols with the same description, they are different values
    - symbols do not auto-convert to a string. you have to call its toString() method, or its description

    .. code-block:: python
        :linenos:

        let id = Symbol("id");
        alert(id.toString()); // Symbol(id), now it works
        alert(id.description); // id

Hidden properties
-----------------
    - symbols allow us to create hidden properties of an object, that no other part of code can occasionally access or 
      overwrite

    - for instance, if we want to store an identifier for am object user, we can use a symbol as a key:

    .. code-block:: python
        :linenos:

        let user = { name: "John" };
        let id = Symbol("id");

        user[id] = "ID Value";
        alert( user[id] ); // we can access the data using the symbol as the key

    - if two scripts needs to set their own id for the user object, setting as symbol will not interfer one with the other. 
      But by setting as simple string, the second one will overwrite the first value

    - symbols can be used in a literal
    - symbols are skipped by for..in
    - Object.assign copies both string and symbol properties

Global symbols
--------------
    - there is a global symbol registry
    - we can create symbols in it and access them later
    - in order to create or read a symbol in the registry, use "Symbol.for(key)"

    .. code-block:: python
        :linenos:

        // read from the global registry
        let id = Symbol.for("id"); // if the symbol did not exist, it is created

        // read it again
        let idAgain = Symbol.for("id");

        // the same symbol
        alert( id === idAgain ); // true

    - there is also a revers method that gives the name for the key: Symbol.keysFor(sym):
        - it uses the global symbol registry to lok the key for the symbol
        - used for local symbols will return undefiend

    .. code-block:: python
        :linenos:

        let sym = Symbol.for("name");
        let sym2 = Symbol.for("id");

        // get name from symbol
        alert( Symbol.keyFor(sym) ); // name
        alert( Symbol.keyFor(sym2) ); // id

System symbols
--------------
    - there exist many system symbols that JavaScript uses internally, and we can use them to fine-tune various aspects of 
      our objects

    - like:
        - Symbol.hasInstance
        - Symbol.isConcatSpreadable
        - Symbol.iterator
        - Symbol.toPrimitive
        - ....

:ref:`Go Back <javascript-types-label>`.
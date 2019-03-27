.. _javascript-objects-label:

Objects
=======
    - everything in Javascript is Object except string, number, boolean, null and undefined
    - objects are used to store keyed collections of various data and more complex entities
    - an object can be created with "{}" with an optional list of properties
    - a property es a "key:value" pair, where key is a string and value can be anything

    .. code-block:: python
        :linenos:

        const user = new Object(); // "object constructor" syntax
        const user = {};  // "object literal" syntax

Literals and properties
-----------------------
    - properties are accessible using dot notation

    .. code-block:: python
        :linenos:

        let user = {     // an object
            name: "John",  // by key "name" store value "John"
            age: 30        // by key "age" store value 30
        };

        // get fields of the object:
        alert( user.name ); // John
        alert( user.age ); // 30

    - to remote a property, we can use "delete" operator

    .. code-block:: python
        :linenos:

        delete user.age;

    - we can use multiword property name, but they mmust be quoted

    .. code-block:: python
        :linenos:

        let user = {
            name: "John",
            age: 30,
            "likes birds": true  // multiword property name must be quoted
        };

Square brackets
---------------
    - for multiword properties, the dot access doesn't work

    .. code-block:: python
        :linenos:

        let user = {};

        // set
        user["likes birds"] = true;

        // get
        alert(user["likes birds"]); // true

        // delete
        delete user["likes birds"];

    - square brackets provide a way to optain the property name as result of any expression:
        - in this case, key may be calculated at run-time or depend on the user input

    .. code-block:: python
        :linenos:

        let key = "likes birds";

        // same as user["likes birds"] = true;
        user[key] = true;

Computed properties
-------------------
    - we can use square brackets in an object literal
    - property name can be set from variables

    .. code-block:: python
        :linenos:

        let fruit = prompt("Which fruit to buy?", "apple");

        let bag = {
          [fruit]: 5, // the name of the property is taken from the variable fruit
        };

        alert( bag.apple ); // 5 if fruit="apple"

Reserved words are allowed
--------------------------
    - a variable cannot have a name equal to one of language-reserved workds like "for", "let"
    - object properties can have

    .. code-block:: python
        :linenos:

        let obj = {
            for: 1,
            let: 2,
            return: 3
        };

        alert( obj.for + obj.let + obj.return );  // 6

    - any name is allowed, expect "__photo__"

    .. code-block:: python
        :linenos:

        let obj = {};
        obj.__proto__ = 5;
        alert(obj.__proto__); // [object Object], didn't work as intended

Property value shorthand
------------------------
    - instead of writing :

    .. code-block:: python
        :linenos:

        function makeUser(name, age) {
            return {
                name: name,
                age: age
                // ...other properties
            };
        }

    - you could write:

    .. code-block:: python
        :linenos:

        let user = {
            name,  // same as name:name
            age: 30
        };

    - we can use both normal properties and shorthands in the same object:

Existence check
---------------
    - it is possible to access any property
    - there will be no error if the property doesn't exist, undefined will be returned

    .. code-block:: python
        :linenos:

        let user = {};

        alert( user.noSuchProperty === undefined ); // true means "no such property"

    - there also exists a special operator "in" to check for existence of a property:

    .. code-block:: python
        :linenos:

        let user = { name: "John", age: 30 };

        alert( "age" in user ); // true, user.age exists
        alert( "blabla" in user ); // false, user.blabla doesn't exist

    - it is recommended to be used "in" comparing to "=== undefined" for properties which are having the value 
      "undefined"

    .. code-block:: python
        :linenos:

        let obj = {
          test: undefined
        };

        alert( obj.test ); // it's undefined, so - no such property?

        alert( "test" in obj ); // true, the property does exist!

The "for ... in" loop
---------------------
    - to walk over all keys of an object there exists a special form of the loop : for...in
    - this is completely different than "for(;;)"

    .. code-block:: python
        :linenos:

        let user = {
            name: "John",
            age: 30,
            isAdmin: true
        };

        for (let key in user) {
            // keys
            alert( key );  // name, age, isAdmin
            // values for the keys
            alert( user[key] ); // John, 30, true
        }

Ordered like an object
----------------------
    - propertie are ordered by :
        - integer properties (strings which are integer) are sorted
        - other appears in creation order

    .. code-block:: python
        :linenos:

        let codes = {
            "49": "Germany",
            "41": "Switzerland",
            "44": "Great Britain",
            // ..,
            "1": "USA"
        };

        for (let code in codes) {
            alert(code); // 1, 41, 44, 49
        }

    .. code-block:: python
        :linenos:

        let user = {
            name: "John",
            surname: "Smith"
        };
        user.age = 25; // add one more

        // non-integer properties are listed in the creation order
        for (let prop in user) {
            alert( prop ); // name, surname, age
        }

Copying by reference
--------------------
    - one of the fundamental differences of objects vs primitives is that they are stored and copied "by reference"
    - 2 objects are equal only if they are the same objects

:ref:`Go Back <javascript-types-label>`.
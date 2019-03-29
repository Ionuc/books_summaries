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

Object to primitive conversion
------------------------------
    - there are cases when objects are threaded with primitive operators
    - there are special mehtods in objects that do the conversion
    - for objects there is no to-boolean coversion because all abjects are true in a boolean context
    - there is only string and number conversions

    - ToPrimitive:
        - when an object is used in the contex where a primitive is required, it's converted to a primitive value using
          the "ToPrimitive" algorithm which is using "hints". There are 3 hints:

            - string: when an operator expects a string, for object-to-string conversions
            - number: when an operator expects a number
            - default: when the oeprator is not sure what type to expect because the operator can be applied for both string
              and number

        - in practice, all built-in objects except for one case (Date) implement "default" conversion the same way as "number"
        - to do the conversion, JavaScript tries to find and call three object methods:
            - call obj[Symbol.toPrimitive](hint) if the method exists
            - otherwise if hint is string try:
                - obj.toString() and obj.valueOf()
            - otherwise if hint is number or default try:
                - obj.valueOf() and obj.toString

    - Symbol.toPrimitive:
        - there is a symbol named "Symbol.toPrimitive"

        .. code-block:: python
            :linenos:

            let user = {
                name: "John",
                money: 1000,

                [Symbol.toPrimitive](hint) {
                    alert(`hint: ${hint}`);
                    return hint == "string" ? `{name: "${this.name}"}` : this.money;
                }
            };

            // conversions demo:
            alert(user); // hint: string -> {name: "John"}
            alert(+user); // hint: number -> 1000
            alert(user + 500); // hint: default -> 1500

    - toString/valueOf
        - these are methods existing a long time ago
        - they provide an alternative "old-style" way to implement the conversion
        - if there's no Symbol.toPrimitive, then JavaScript tries to find them an the order:
            - toString -> valueOf for String hint
            - valueOf -> toString oterwise

        .. code-block:: python
            :linenos:

            let user = {
                name: "John",
                money: 1000,

                // for hint="string"
                toString() {
                    return `{name: "${this.name}"}`;
                },

                // for hint="number" or "default"
                valueOf() {
                    return this.money;
                }
            };

            alert(user); // toString -> {name: "John"}
            alert(+user); // valueOf -> 1000
            alert(user + 500); // valueOf -> 1500

Constructor
-----------
    - the regular "{}" syntax allows to create one object
    - but often we need to create multiple similar objects
    - constructor functions are technically regular functions
    - there are 2 convertions though:
        - they are named with capital letter first
        - they should be executed only with "new" operator

    .. code-block:: python
        :linenos:

        function User(name) {
            this.name = name;
            this.isAdmin = false;
        }

        let user = new User("Jack");

        alert(user.name); // Jack
        alert(user.isAdmin); // false

    - when a function is executed with new, it does the following steps:
        - a new empty object is created an assigned to this
        - the function body executes. Usually it modified this, ads new properties to it
        - the value of this is returned
    - in other words, new User() does something like this:

    .. code-block:: python
        :linenos:

        function User(name) {
          // this = {};  (implicitly)

          // add properties to this
          this.name = name;
          this.isAdmin = false;

          // return this;  (implicitly)
        }

        // te result of new User("Jack) is similar with:
        let user = {
            name: "Jack",
            isAdmin: false
        };

    - any function can be used as a constructor, meaninig any function can be run with new and it will execute the algorithm above

    - Dual-syntax constructor:new.target
        - inside a function we can check whether it was called with new or without it using a special new.target property:
            - empty for regular calls
            - equals the function for "new" call

        .. code-block:: python
            :linenos:

            function User() {
                alert(new.target);
            }

            // without "new":
            User(); // undefined

            // with "new":
            new User(); // function User { ... }

        - this can be used to allow both new and regular to work the same

        .. code-block:: python
            :linenos:

            function User(name) {
                if (!new.target) { // if you run me without new
                    return new User(name); // ...I will add new for you
                }

                this.name = name;
            }

            let john = User("John"); // redirects call to new User
            alert(john.name); // John

    - Return from constructors
        - usually constructors do not have a return statement
        - if there is a reutrn statement then the rule is:
            - if return is called with object, the it is returned instead of this
            - if return is called with a primitive, it's ignored
        - in other words, return with an object returns that object, in all other cases "this" is returned

    - Methods in constructor
        - using constructor functions to create objects gives a great deal of lfexibility
        - we can add ot this not only properties, but aslo methods

        .. code-block:: python
            :linenos:

            function User(name) {
                this.name = name;

                this.sayHi = function() {
                    alert( "My name is: " + this.name );
                };
            }

            let john = new User("John");

            john.sayHi(); // My name is: John

            /*
            john = {
                name: "John",
                sayHi: function() { ... }
            }
            */

:ref:`Go Back <javascript-types-label>`.